package kz.ruanjian.memed.util.json;

public class JsonUtilProcessingException extends RuntimeException {

  public JsonUtilProcessingException() {
  }

  public JsonUtilProcessingException(String message) {
    super(message);
  }

  public JsonUtilProcessingException(String message, Throwable cause) {
    super(message, cause);
  }

  public JsonUtilProcessingException(Throwable cause) {
    super(cause);
  }

  public JsonUtilProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
