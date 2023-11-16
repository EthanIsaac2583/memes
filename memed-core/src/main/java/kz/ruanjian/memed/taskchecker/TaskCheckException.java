package kz.ruanjian.memed.taskchecker;

public class TaskCheckException extends RuntimeException {

  public TaskCheckException() {
  }

  public TaskCheckException(String message) {
    super(message);
  }

  public TaskCheckException(String message, Throwable cause) {
    super(message, cause);
  }

  public TaskCheckException(Throwable cause) {
    super(cause);
  }

  public TaskCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
