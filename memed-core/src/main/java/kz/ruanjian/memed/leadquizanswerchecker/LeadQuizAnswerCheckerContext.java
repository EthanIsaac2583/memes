package kz.ruanjian.memed.leadquizanswerchecker;

import kz.ruanjian.memed.model.LeadQuizAnswer;
import org.springframework.stereotype.Component;

@Component
public class LeadQuizAnswerCheckerContext implements LeadQuizAnswerChecker {

  @Override
  public int check(LeadQuizAnswer answer) {
    return 100;
  }
}
