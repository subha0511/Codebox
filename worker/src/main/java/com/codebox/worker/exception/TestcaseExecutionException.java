package com.codebox.worker.exception;

public class TestcaseExecutionException extends RuntimeException {

  public TestcaseExecutionException(String message) {
    super(message);
  }

  public TestcaseExecutionException(String message, Throwable cause) {
    super(message, cause);
  }
}
