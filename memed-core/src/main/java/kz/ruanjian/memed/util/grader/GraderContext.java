package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import org.springframework.stereotype.Component;

@Component
public class GraderContext implements Grader {

  @Override
  public int grade(Question question) {
    if (question.getAnswer() instanceof SingleChoiceAnswer userSingleChoiceAnswer) {
      if (question.getTask().getAnswer() instanceof SingleChoiceAnswer correctSingleChoiceAnswer) {
        if (userSingleChoiceAnswer.equals(correctSingleChoiceAnswer)) {
          return 100;
        }

        return 0;
      }
    }

    return 0;
  }
}
