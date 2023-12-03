package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceAnswerGrader implements Grader {

  private final MemedProperties memedProperties;

  public MultipleChoiceAnswerGrader(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  @Override
  public int grade(Question question) {
    if (isCorrectAnswer(question)) {
      return memedProperties.getGradeMax();
    }

    return memedProperties.getGradeMin();
  }

  private boolean isCorrectAnswer(Question question) {
    Answer correctAnswer = question.getTask().getAnswer();
    Answer userAnswer = question.getAnswer();

    return correctAnswer.equals(userAnswer);
  }
}