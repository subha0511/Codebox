package com.codebox.worker.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse.ContainerState;
import com.github.dockerjava.api.model.HostConfig;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class ContainerPoolConfig {

  private final ScheduledExecutorService healthCheckExecutor = Executors.newSingleThreadScheduledExecutor();

  @Autowired
  DockerConfigurationProperites dockerConfigurationProperites;

  @Autowired
  DockerClient dockerClient;

  private BlockingQueue<String> containerPool;

  @PostConstruct
  public void initContainerPool() {
    ensureImageExists();
    containerPool = new LinkedBlockingQueue<>(dockerConfigurationProperites.getPool()
        .getSize());
    String IMAGE_NAME = dockerConfigurationProperites.getPool()
        .getImageName();

    for (int i = 0; i < dockerConfigurationProperites.getPool()
        .getSize(); i++) {
      CreateContainerResponse containerResponse = dockerClient.createContainerCmd(IMAGE_NAME)
          .withHostConfig(HostConfig.newHostConfig()
              .withMemory(256 * 1024 * 1024L) // Limit memory to 256MB
              .withCpuCount(1L) // Limit CPU to 1 core
          )
          .withTty(true)
          .withAttachStdin(true)
          .withAttachStdout(true)
          .withAttachStderr(true)
          .exec();
      String containerId = containerResponse.getId();
      dockerClient.startContainerCmd(containerId)
          .exec(); // Start the container
      containerPool.add(containerId); // Add to the pool
    }
    System.out.println("Container pool initialized with " + dockerConfigurationProperites.getPool()
        .getSize() + " containers.");
    startHealthChecks();
  }

  private void startHealthChecks() {
    healthCheckExecutor.scheduleAtFixedRate(this::performHealthCheck, 1, 30, TimeUnit.SECONDS);
  }

  private void performHealthCheck() {
    synchronized (containerPool) {
      Iterator<String> iterator = containerPool.iterator();
      while (iterator.hasNext()) {
        String containerId = iterator.next();
        try {

          InspectContainerResponse container = dockerClient.inspectContainerCmd(containerId)
              .exec();
          ContainerState state = container.getState();
          if (state == null || Boolean.FALSE.equals(state.getRunning())) {
            System.out.println("Container " + containerId + " is not running. Removing from pool.");
            iterator.remove();
            removeContainer(containerId);
            addHealthyContainer();
          }
        } catch (Exception e) {
          System.out.println("Error checking health of container: " + e.getMessage());
          addHealthyContainer();
        }
      }
    }
  }

  private void removeContainer(String containerId) {
    try {
      dockerClient.stopContainerCmd(containerId)
          .exec();
      dockerClient.removeContainerCmd(containerId)
          .exec();
      System.out.println("Stopped and removed container: " + containerId);
    } catch (Exception e) {
      System.err.println(
          "Error stopping/removing container " + containerId + ": " + e.getMessage());
    }
  }

  private void ensureImageExists() {
    String IMAGE_NAME = dockerConfigurationProperites.getPool()
        .getImageName();
    try {
      boolean imageExists = dockerClient.listImagesCmd()
          .withImageNameFilter(IMAGE_NAME)
          .exec()
          .stream()
          .anyMatch(image -> image.getRepoTags() != null && Arrays.asList(image.getRepoTags())
              .contains(IMAGE_NAME + ":latest"));

      if (!imageExists) {
        System.out.println("Building image: " + IMAGE_NAME);

        String dockerfilePath = "worker/src/main/java/com/codebox/worker/docker_images/worker.dockerfile";
        File dockerfile = new File(dockerfilePath);

        if (!dockerfile.exists()) {
          throw new RuntimeException("Dockerfile not found at: " + dockerfile.getAbsolutePath());
        }

        dockerClient.buildImageCmd()
            .withDockerfile(dockerfile)
            .withTags(Set.of(IMAGE_NAME + ":latest"))
            .start()
            .awaitCompletion();
        System.out.println("Image built successfully");
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to ensure image exists: " + e.getMessage(), e);
    }
  }

  private void addHealthyContainer() {
    CreateContainerResponse containerResponse = dockerClient.createContainerCmd(
            dockerConfigurationProperites.getPool()
                .getImageName())
        .withHostConfig(HostConfig.newHostConfig()
            .withMemory(256 * 1024 * 1024L) // Limit memory to 256MB
            .withCpuCount(1L) // Limit CPU to 1 core
        )
        .withTty(true)
        .withAttachStdin(true)
        .withAttachStdout(true)
        .withAttachStderr(true)
        .exec();
    String containerId = containerResponse.getId();
    dockerClient.startContainerCmd(containerId)
        .exec(); // Start the container
    containerPool.add(containerId); // Add to the pool
    System.out.println("Added container " + containerId + " to the pool.");
  }

  @PreDestroy
  public void destroyPool() throws IOException {
    healthCheckExecutor.shutdown();
    if (dockerClient != null) {
      containerPool.forEach(containerId -> {
        try {
          dockerClient.stopContainerCmd(containerId)
              .exec();
          dockerClient.removeContainerCmd(containerId)
              .exec();
          System.out.println("Stopped and removed container: " + containerId);
        } catch (Exception e) {
          System.err.println(
              "Error stopping/removing container " + containerId + ": " + e.getMessage());
        }
      });
      dockerClient.close();
    }
    System.out.println("Container pool destroyed.");
  }

  @Bean("ContainerPool")
  public BlockingQueue<String> containerPool() {
    return containerPool;
  }

}
