package com.codebox.worker.exception;

public class TimeLimitExceededException extends Exception{

  public TimeLimitExceededException() {
    super("Time Limit Exceeded for the testcase");
  }

  public TimeLimitExceededException(String message) {
    super(message);
  }

  public TimeLimitExceededException(String message,Throwable cause) {
    super(message, cause);
  }

}
