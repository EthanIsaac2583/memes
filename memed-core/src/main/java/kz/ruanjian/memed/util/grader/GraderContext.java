package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.BlankType;
import org.springframework.stereotype.Component;

@Component
public class GraderContext implements Grader {

  private static final String NO_GRADER = "There is no grader to handle question";

  private final SingleChoiceAnswerGrader singleChoiceAnswerGrader;
  private final MultipleChoiceAnswerGrader multipleChoiceAnswerGrader;

  public GraderContext(SingleChoiceAnswerGrader singleChoiceAnswerGrader,
                       MultipleChoiceAnswerGrader multipleChoiceAnswerGrader) {
    this.singleChoiceAnswerGrader = singleChoiceAnswerGrader;
    this.multipleChoiceAnswerGrader = multipleChoiceAnswerGrader;
  }

  @Override
  public int grade(Question question) {
    if (BlankType.SINGLE_CHOICE.equals(question.getAnswer().getType())) {
      return singleChoiceAnswerGrader.grade(question);
    }

    if (BlankType.MULTIPLE_CHOICE.equals(question.getAnswer().getType())) {
      return multipleChoiceAnswerGrader.grade(question);
    }

    throw new GraderException(NO_GRADER);
  }
}
