package kz.ruanjian.memed.pojo.json;

public class PojoProcessException extends RuntimeException {

  public PojoProcessException() {
  }

  public PojoProcessException(String message) {
    super(message);
  }

  public PojoProcessException(String message, Throwable cause) {
    super(message, cause);
  }

  public PojoProcessException(Throwable cause) {
    super(cause);
  }

  public PojoProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
