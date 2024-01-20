package kz.ruanjian.memed.util.grader;

public class GraderMismatchException extends GraderException {

  public GraderMismatchException() {
  }

  public GraderMismatchException(String message) {
    super(message);
  }

  public GraderMismatchException(String message, Throwable cause) {
    super(message, cause);
  }

  public GraderMismatchException(Throwable cause) {
    super(cause);
  }

  public GraderMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
