package kz.ruanjian.memed.util.grader;

public class GraderException extends RuntimeException {

  public GraderException() {
  }

  public GraderException(String message) {
    super(message);
  }

  public GraderException(String message, Throwable cause) {
    super(message, cause);
  }

  public GraderException(Throwable cause) {
    super(cause);
  }

  public GraderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
