package kz.ruanjian.memed.util.json;

public class JsonProcessException extends RuntimeException {

  public JsonProcessException() {
  }

  public JsonProcessException(String message) {
    super(message);
  }

  public JsonProcessException(String message, Throwable cause) {
    super(message, cause);
  }

  public JsonProcessException(Throwable cause) {
    super(cause);
  }

  public JsonProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
