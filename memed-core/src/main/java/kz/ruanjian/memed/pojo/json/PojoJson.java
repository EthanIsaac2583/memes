package kz.ruanjian.memed.pojo.json;

import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.BodyType;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.answer.MultipleChoiceAnswer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.blank.MultipleChoiceBlank;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.body.ImageBody;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
import kz.ruanjian.memed.pojo.body.YoutubeVideoBody;
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

    if (BlankType.MULTIPLE_CHOICE.equals(answer.getType())) {
      return jsonUtil.parse(stringedAnswer, MultipleChoiceAnswer.class);
    }

    throw new PojoProcessException("Can not process answer");
  }

  public Blank parseBlank(String stringedBlank) {
    Blank blank = jsonUtil.parse(stringedBlank, Blank.class);

    if (blank == null) {
      return null;
    }

    if (BlankType.SINGLE_CHOICE.equals(blank.getType())) {
      return jsonUtil.parse(stringedBlank, SingleChoiceBlank.class);
    }

    if (BlankType.MULTIPLE_CHOICE.equals(blank.getType())) {
      return jsonUtil.parse(stringedBlank, MultipleChoiceBlank.class);
    }

    throw new PojoProcessException("Can not process blank");
  }

  public Body parseBody(String stringedBody) {
    Body body = jsonUtil.parse(stringedBody, Body.class);

    if (body== null) {
      return null;
    }

    if (BodyType.PLAIN_TEXT.equals(body.getType())) {
      return jsonUtil.parse(stringedBody, PlainTextBody.class);
    }

    if (BodyType.IMAGE.equals(body.getType())) {
      return jsonUtil.parse(stringedBody, ImageBody.class);
    }

    if (BodyType.YOUTUBE_VIDEO.equals(body.getType())) {
      return jsonUtil.parse(stringedBody, YoutubeVideoBody.class);
    }

    throw new PojoProcessException("Can not process body");
  }
}
