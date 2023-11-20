package kz.ruanjian.memed.leadquizanswerchecker;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.LeadQuizAnswer;
import org.springframework.stereotype.Component;

@Component
public class LeadQuizAnswerCheckerContext implements LeadQuizAnswerChecker {

  private final MemedProperties memedProperties;

  public LeadQuizAnswerCheckerContext(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  @Override
  public int check(LeadQuizAnswer answer) {
    return memedProperties.getGradeMax();
  }
}
