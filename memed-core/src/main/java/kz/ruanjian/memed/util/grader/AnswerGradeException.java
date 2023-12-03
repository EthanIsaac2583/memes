package kz.ruanjian.memed.util.grader;

public class AnswerGradeException extends RuntimeException {

  public AnswerGradeException() {
  }

  public AnswerGradeException(String message) {
    super(message);
  }

  public AnswerGradeException(String message, Throwable cause) {
    super(message, cause);
  }

  public AnswerGradeException(Throwable cause) {
    super(cause);
  }

  public AnswerGradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
