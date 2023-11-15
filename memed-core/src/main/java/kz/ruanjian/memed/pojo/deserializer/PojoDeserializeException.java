package kz.ruanjian.memed.pojo.deserializer;

public class PojoDeserializeException extends RuntimeException {

  public PojoDeserializeException() {
  }

  public PojoDeserializeException(String message) {
    super(message);
  }

  public PojoDeserializeException(String message, Throwable cause) {
    super(message, cause);
  }

  public PojoDeserializeException(Throwable cause) {
    super(cause);
  }

  public PojoDeserializeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
