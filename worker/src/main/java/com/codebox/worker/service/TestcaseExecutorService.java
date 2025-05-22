package com.codebox.worker.service;

import com.codebox.worker.domain.dto.SubmissionDTO;
import com.codebox.worker.domain.dto.SubmissionTestcaseDTO;
import com.codebox.worker.exception.MemoryLimitExceededException;
import com.codebox.worker.exception.TestcaseExecutionException;
import com.codebox.worker.exception.TestcaseRuntimeException;
import com.codebox.worker.exception.TimeLimitExceededException;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.InspectExecResponse;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TestcaseExecutorService {

  @Autowired
  @Qualifier("ContainerPool")
  BlockingQueue<String> containerPool;

  @Autowired
  @Qualifier("TestcaseExecutorPool")
  ExecutorService executorService;

  @Autowired
  DockerClient dockerClient;

  public SubmissionDTO processSubmission(SubmissionDTO submissionDTO) {
    String code = submissionDTO.getCode();
    List<SubmissionTestcaseDTO> testCases = submissionDTO.getSubmissionTestcases();

    List<CompletableFuture<SubmissionTestcaseDTO>> futures = testCases.stream()
        .map(testcase -> CompletableFuture.supplyAsync(
            () -> {
              try {
                var execOutput = executeTestCase(code, testcase, submissionDTO.getLanguage());
                testcase.setOutput(execOutput);
                if (execOutput.equals(testcase.getExpectedOutput()
                    .trim())) {
                  testcase.setStatus("AC");
                } else {
                  testcase.setStatus("WA");
                }
              } catch (TimeLimitExceededException e) {
                testcase.setOutput("Time limit Exceeded");
                testcase.setStatus("TLE");
              } catch (MemoryLimitExceededException e) {
                testcase.setStatus("Memory Limit Exceeded");
                testcase.setStatus("MLE");
              } catch (TestcaseRuntimeException e) {
                testcase.setOutput(e.getMessage());
                testcase.setStatus("RTE");
              } catch (TestcaseExecutionException e) {
                testcase.setOutput(e.getMessage());
                testcase.setStatus("ERROR");
              }
              return testcase;
            }, executorService))
        .toList();

    CompletableFuture<Void> allFutures = CompletableFuture.allOf(
        futures.toArray(new CompletableFuture[0]));

    try {
      allFutures.join();

      var finalTestcases = futures.stream()
          .map(CompletableFuture::join)
          .toList();

      String status = "AC";
      Boolean errorFlag = false;

      for (SubmissionTestcaseDTO testcase : finalTestcases) {
        if (!testcase.getStatus()
            .equals("AC")) {
          status = testcase.getStatus();
          break;
        }
      }

      submissionDTO.setStatus(status);
      submissionDTO.setSubmissionTestcases(finalTestcases);

      return submissionDTO;
    } catch (Exception e) {
      throw new RuntimeException("Error in executing code in container" + e);
    }
  }

  private String executeTestCase(String code, SubmissionTestcaseDTO testcase, String language)
      throws TimeLimitExceededException, TestcaseExecutionException, TestcaseRuntimeException, MemoryLimitExceededException {

    String containerId = null;
    try {
      containerId = containerPool.take();
      return executeCodeInContainer(containerId, code, testcase.getInput(), language);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      if (containerId != null) {
        containerPool.add(containerId);
      }
    }
  }

  private String executeCodeInContainer(
      String containerId, String code, String input, String language)
      throws TimeLimitExceededException, TestcaseExecutionException, TestcaseRuntimeException {

    String execCmd = getExecutionCommand(language, code, input);
    ExecCreateCmdResponse execCreateCmdResponse;

    try {
      execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
          .withAttachStdout(true)
          .withAttachStderr(true)
          .withCmd("sh", "-c", execCmd)
          .exec();
    } catch (Exception e) {
      throw new TestcaseExecutionException("Failed to create execution command: " + e.getMessage());
    }

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); ByteArrayOutputStream errorStream = new ByteArrayOutputStream()) {

      boolean completed = dockerClient.execStartCmd(execCreateCmdResponse.getId())
          .exec(new ExecStartResultCallback(outputStream, errorStream))
          .awaitCompletion(1, TimeUnit.SECONDS);

      if (!completed) {
        throw new TimeLimitExceededException();
      }

      String error = errorStream.toString()
          .trim();
      if (!error.isEmpty()) {
        throw new TestcaseRuntimeException(error);
      }

      InspectExecResponse execInspect = dockerClient.inspectExecCmd(execCreateCmdResponse.getId())
          .exec();
      if (execInspect.getExitCodeLong() != 0L) {
        throw new TestcaseRuntimeException(outputStream.toString()
            .trim());
      }

      return outputStream.toString()
          .trim();

    } catch (TimeLimitExceededException | TestcaseRuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new TestcaseExecutionException("Error executing code: " + e.getMessage());
    }
  }

  private String getExecutionCommand(String language, String code, String input) {
    StringBuilder command = new StringBuilder();

    // Write the code to a file inside the container
    switch (language.toLowerCase()) {
      case "python":
        command.append("echo '")
            .append(code.replace("'", "'\\''"))
            .append("' > /tmp/code.py &&")
            .append(String.format("echo '%s' | python /tmp/code.py", input.replace("'", "'\\''")));
        break;
      case "java":
        command.append("echo '")
            .append(code.replace("'", "'\\''"))
            .append("' > /tmp/Main.java && ")
            .append("javac /tmp/Main.java &&")
            .append(String.format("echo '%s' | java -cp /tmp Main", input.replace("'", "'\\''")));
        break;
      case "cpp":
        command.append("echo '")
            .append(code.replace("'", "'\\''"))
            .append("' > /tmp/main.cpp && ")
            .append("g++ /tmp/main.cpp -o /tmp/main &&")
            .append(String.format("echo '%s' | /tmp/main", input.replace("'", "'\\''")));
        break;
      default:
        throw new IllegalArgumentException("Unsupported language: " + language);
    }

    return command.toString();
  }
}
