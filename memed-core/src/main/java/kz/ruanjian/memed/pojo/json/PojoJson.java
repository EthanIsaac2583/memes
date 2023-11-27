package kz.ruanjian.memed.pojo.json;

import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.util.json.JsonUtil;
import org.springframework.stereotype.Component;

@Component
public class PojoJson {

  private final JsonUtil jsonUtil;

  public PojoJson(JsonUtil jsonUtil) {
    this.jsonUtil = jsonUtil;
  }

  public String stringify(Object value) {
    return jsonUtil.stringify(value);
  }

  public Answer parseAnswer(String stringedAnswer) {
    Answer answer = jsonUtil.parse(stringedAnswer, Answer.class);

    if (answer == null) {
      return null;
    }

    if (BlankType.SINGLE_CHOICE.equals(answer.getType())) {
      return jsonUtil.parse(stringedAnswer, SingleChoiceAnswer.class);
    }

    throw new PojoProcessException("Can not process answer");
  }
}
