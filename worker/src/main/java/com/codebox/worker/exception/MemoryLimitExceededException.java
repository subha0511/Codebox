package com.codebox.worker.exception;

public class MemoryLimitExceededException extends Exception{

  public MemoryLimitExceededException() {
    super("Time Limit Exceeded for the testcase");
  }

  public MemoryLimitExceededException(String message) {
    super(message);
  }

  public MemoryLimitExceededException(String message,Throwable cause) {
    super(message, cause);
  }

}
