package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import org.springframework.stereotype.Component;

@Component
public class SingleChoiceAnswerGrader implements Grader {

  private final MemedProperties memedProperties;

  public SingleChoiceAnswerGrader(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  @Override
  public int grade(Question question) {
    verifyAnswerClass(question);

    if (isCorrect(question)) {
      return memedProperties.getApplication().getGradeMax();
    }

    return memedProperties.getApplication().getGradeMin();
  }

  private boolean isCorrect(Question question) {
    Answer correctAnswer = question.getTask().getAnswer();
    Answer userAnswer = question.getAnswer();

    return correctAnswer.equals(userAnswer);
  }

  private void verifyAnswerClass(Question question) {
    if (question.getAnswer().getClass() != SingleChoiceAnswer.class) {
      throw new GraderMismatchException("Single choice answer required");
    }
  }
}
