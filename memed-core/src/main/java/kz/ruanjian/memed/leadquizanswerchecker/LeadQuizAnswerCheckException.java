package kz.ruanjian.memed.leadquizanswerchecker;

public class LeadQuizAnswerCheckException extends RuntimeException {

  public LeadQuizAnswerCheckException() {
  }

  public LeadQuizAnswerCheckException(String message) {
    super(message);
  }

  public LeadQuizAnswerCheckException(String message, Throwable cause) {
    super(message, cause);
  }

  public LeadQuizAnswerCheckException(Throwable cause) {
    super(cause);
  }

  public LeadQuizAnswerCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
