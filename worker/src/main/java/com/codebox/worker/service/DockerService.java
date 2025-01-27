// package com.codebox.worker.service;
//
// import com.codebox.shared_dtos.schema.SubmissionTestcase;
// import com.github.dockerjava.api.DockerClient;
// import com.github.dockerjava.api.command.CreateContainerResponse;
// import com.github.dockerjava.api.command.ExecCreateCmdResponse;
// import com.github.dockerjava.api.command.InspectExecResponse;
// import com.github.dockerjava.api.model.HostConfig;
// import com.github.dockerjava.core.command.ExecStartResultCallback;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// @Service
// public class DockerService {
//
//    @Autowired
//    private DockerClient dockerClient;
//
//    public String executeCodeInContainer(String code, List<SubmissionTestcase> testCases, String language)
//            throws IOException, InterruptedException {
//
//        String image = getDockerImage(language);
//        dockerClient.pullImageCmd(image).start().awaitCompletion();
//
//        // Step 2: Create a container
//        CreateContainerResponse container = dockerClient
//                .createContainerCmd(image)
//                .withHostConfig(
//                        HostConfig.newHostConfig()
//                                .withMemory(256 * 1024 * 1024L) // Limit memory to 256MB
//                                .withCpuCount(1L) // Limit CPU to 1 core
//                        )
//                .withTty(true)
//                .withAttachStdin(true)
//                .withAttachStdout(true)
//                .withAttachStderr(true)
//                .exec();
//
//        // Step 3: Start the container
//        dockerClient.startContainerCmd(container.getId()).exec();
//
//        // Step 4: Prepare the execution command
//        String executionCommand = getExecutionCommand(language, code, testCases);
//
//        // Step 5: Execute the command in the container
//        ExecCreateCmdResponse execCreateCmdResponse = dockerClient
//                .execCreateCmd(container.getId())
//                .withAttachStdout(true)
//                .withAttachStderr(true)
//                .withCmd("sh", "-c", executionCommand)
//                .exec();
//
//        // Step 6: Capture the output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        dockerClient
//                .execStartCmd(execCreateCmdResponse.getId())
//                .exec(new ExecStartResultCallback(outputStream, System.err))
//                .awaitCompletion();
//
//        // Step 7: Inspect the execution result
//        InspectExecResponse inspectExecResponse =
//                dockerClient.inspectExecCmd(execCreateCmdResponse.getId()).exec();
//        if (inspectExecResponse.getExitCode() != 0) {
//            throw new RuntimeException("Execution failed with exit code: " + inspectExecResponse.getExitCode());
//        }
//
//        // Step 8: Stop and remove the container
//        dockerClient.stopContainerCmd(container.getId()).exec();
//        dockerClient.removeContainerCmd(container.getId()).exec();
//
//        return outputStream.toString();
//    }
//
//    private String getDockerImage(String language) {
//        return switch (language.toLowerCase()) {
//            case "python" -> "python:3.12.8-slim";
//            case "java" -> "openjdk:11";
//            case "cpp" -> "gcc:latest";
//            default -> throw new IllegalArgumentException("Unsupported language: " + language);
//        };
//    }
//
//    private String getExecutionCommand(String language, String code, List<SubmissionTestcase> testCases) {
//        StringBuilder command = new StringBuilder();
//
//        // Write the code to a file inside the container
//        switch (language.toLowerCase()) {
//            case "python":
//                command.append("echo '").append(code.replace("'", "'\\''")).append("' > /tmp/code.py && ");
//                command.append("python /tmp/code.py <<EOF\n");
//                break;
//            case "java":
//                command.append("echo '").append(code.replace("'", "'\\''")).append("' > /tmp/Main.java && ");
//                command.append("javac /tmp/Main.java && ");
//                command.append("java -cp /tmp Main <<EOF\n");
//                break;
//            case "cpp":
//                command.append("echo '").append(code.replace("'", "'\\''")).append("' > /tmp/main.cpp && ");
//                command.append("g++ /tmp/main.cpp -o /tmp/main && ");
//                command.append("/tmp/main <<EOF\n");
//                break;
//            default:
//                throw new IllegalArgumentException("Unsupported language: " + language);
//        }
//
//        // Append all test case inputs
//        for (SubmissionTestcase testCase : testCases) {
//            command.append(testCase.getInput()).append("\n");
//        }
//        command.append("EOF");
//
//        return command.toString();
//    }
// }
