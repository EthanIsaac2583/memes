package kz.ruanjian.memed.util.grader;

public class GraderTypeMismatch extends RuntimeException {

  public GraderTypeMismatch() {
  }

  public GraderTypeMismatch(String message) {
    super(message);
  }

  public GraderTypeMismatch(String message, Throwable cause) {
    super(message, cause);
  }

  public GraderTypeMismatch(Throwable cause) {
    super(cause);
  }

  public GraderTypeMismatch(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
