package kz.ruanjian.memed.leadquizanswerchecker;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.LeadQuizAnswer;
import kz.ruanjian.memed.pojo.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class SingleChoiceBlankChecker implements LeadQuizAnswerChecker {

  private final MemedProperties memedProperties;

  public SingleChoiceBlankChecker(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  @Override
  public int check(LeadQuizAnswer answer) {
    if (isCorrect(answer)) {
      return memedProperties.getGradeMax();
    }

    return memedProperties.getGradeMin();
  }

  private boolean isCorrect(LeadQuizAnswer answer) {
    Answer correctAnswer = answer.getTask().getAnswer();
    Answer userAnswer = answer.getAnswer();

    return correctAnswer.equals(userAnswer);
  }
}
