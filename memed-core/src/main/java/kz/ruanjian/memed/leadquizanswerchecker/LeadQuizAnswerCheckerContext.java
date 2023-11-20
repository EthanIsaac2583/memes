package kz.ruanjian.memed.leadquizanswerchecker;

import kz.ruanjian.memed.model.LeadQuizAnswer;
import kz.ruanjian.memed.pojo.BlankType;
import org.springframework.stereotype.Component;

@Component
public class LeadQuizAnswerCheckerContext implements LeadQuizAnswerChecker {

  private static final String NO_CHECKER_EXCEPTION = "There is no answer checker";

  private final SingleChoiceBlankChecker singleChoiceBlankChecker;

  public LeadQuizAnswerCheckerContext(SingleChoiceBlankChecker singleChoiceBlankChecker) {
    this.singleChoiceBlankChecker = singleChoiceBlankChecker;
  }

  @Override
  public int check(LeadQuizAnswer answer) {
    if (BlankType.SINGLE_CHOICE.equals(answer.getAnswer().getType())) {
      return singleChoiceBlankChecker.check(answer);
    }

    throw new LeadQuizAnswerCheckException(NO_CHECKER_EXCEPTION);
  }
}
