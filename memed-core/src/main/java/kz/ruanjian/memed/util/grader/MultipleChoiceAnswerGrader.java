package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.answer.MultipleChoiceAnswer;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceAnswerGrader implements Grader {

  private final MemedProperties memedProperties;

  public MultipleChoiceAnswerGrader(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  @Override
  public int grade(Question question) {
    if (isCorrect(question)) {
      return memedProperties.getApplication().getGradeMax();
    }

    return memedProperties.getApplication().getGradeMin();
  }

  private boolean isCorrect(Question question) {
    MultipleChoiceAnswer correctAnswer = resolveCorrectAnswer(question);
    MultipleChoiceAnswer userAnswer = resolveUserAnswer(question);

    return userAnswer.getKeys().stream()
      .anyMatch(key -> correctAnswer.getKeys().contains(key));
  }

  private MultipleChoiceAnswer resolveCorrectAnswer(Question question) {
    if (question.getTask().getAnswer() instanceof MultipleChoiceAnswer correctAnswer) {
      return correctAnswer;
    }

    throw new GraderException("Incorrect answer type");
  }

  public MultipleChoiceAnswer resolveUserAnswer(Question question) {
    if (question.getAnswer() instanceof MultipleChoiceAnswer userAnswer) {
      return userAnswer;
    }

    throw new GraderException("Incorrect answer type");
  }
}
