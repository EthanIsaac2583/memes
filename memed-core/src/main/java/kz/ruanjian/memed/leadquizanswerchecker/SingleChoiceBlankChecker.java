package kz.ruanjian.memed.leadquizanswerchecker;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class SingleChoiceBlankChecker implements LeadQuizAnswerChecker {

  private final MemedProperties memedProperties;

  public SingleChoiceBlankChecker(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  @Override
  public int check(Question answer) {
    if (isCorrect(answer)) {
      return memedProperties.getGradeMax();
    }

    return memedProperties.getGradeMin();
  }

  private boolean isCorrect(Question answer) {
    Answer correctAnswer = answer.getTask().getAnswer();
    Answer userAnswer = answer.getAnswer();

    return correctAnswer.equals(userAnswer);
  }
}
