package kz.ruanjian.memed.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataConflictException extends ServiceException {

  public DataConflictException() {
  }

  public DataConflictException(String message) {
    super(message);
  }

  public DataConflictException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataConflictException(Throwable cause) {
    super(cause);
  }

  public DataConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
