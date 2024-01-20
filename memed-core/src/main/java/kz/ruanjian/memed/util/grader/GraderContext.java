package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.BlankType;
import org.springframework.stereotype.Component;

@Component
public class GraderContext implements Grader {

  private static final String NO_GRADER = "There is no grader to handle question";

  private final SingleChoiceAnswerGrader singleChoiceAnswerGrader;

  public GraderContext(SingleChoiceAnswerGrader singleChoiceAnswerGrader) {
    this.singleChoiceAnswerGrader = singleChoiceAnswerGrader;
  }

  @Override
  public int grade(Question question) {
    if (BlankType.SINGLE_CHOICE.equals(question.getAnswer().getType())) {
      return singleChoiceAnswerGrader.grade(question);
    }

    throw new GraderException(NO_GRADER);
  }
}
