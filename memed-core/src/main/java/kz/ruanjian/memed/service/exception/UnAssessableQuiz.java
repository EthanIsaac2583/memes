package kz.ruanjian.memed.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UnAssessableQuiz extends ServiceException {

  public UnAssessableQuiz() {
  }

  public UnAssessableQuiz(String message) {
    super(message);
  }

  public UnAssessableQuiz(String message, Throwable cause) {
    super(message, cause);
  }

  public UnAssessableQuiz(Throwable cause) {
    super(cause);
  }

  public UnAssessableQuiz(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
