package kz.ruanjian.memed.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationFailedException extends ServiceException {

  public AuthenticationFailedException() {
  }

  public AuthenticationFailedException(String message) {
    super(message);
  }

  public AuthenticationFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public AuthenticationFailedException(Throwable cause) {
    super(cause);
  }

  public AuthenticationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
