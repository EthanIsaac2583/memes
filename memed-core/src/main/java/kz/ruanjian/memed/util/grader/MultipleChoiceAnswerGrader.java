package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceAnswerGrader implements Grader {

  @Override
  public int grade(Question question) {
    if (isCorrectAnswer(question)) {
      return 100;
    }

    return 0;
  }

  private boolean isCorrectAnswer(Question question) {
    Answer correctAnswer = question.getTask().getAnswer();
    Answer userAnswer = question.getAnswer();

    return correctAnswer.equals(userAnswer);
  }
}
