package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceAnswerGrader implements Grader {

  @Override
  public int grade(Question question) {
    return 0;
  }
}
