package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.BodyType;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.body.YoutubeVideoBody;
import kz.ruanjian.memed.util.json.JsonUtil;
import kz.ruanjian.memed.util.json.JsonProcessException;
import org.springframework.stereotype.Component;

@Component
public class BodyConverter implements AttributeConverter<Body, String> {

  private final JsonUtil jsonUtil;

  public BodyConverter(JsonUtil jsonUtil) {
    this.jsonUtil = jsonUtil;
  }

  @Override
  public String convertToDatabaseColumn(Body body) {
    if (body== null) {
      return null;
    }

    return jsonUtil.stringify(body);
  }

  @Override
  public Body convertToEntityAttribute(String s) {
    Body body = jsonUtil.parse(s, Body.class);

    if (body== null) {
      return null;
    }

    if (BodyType.PLAIN_TEXT.equals(body.getType())) {
      return jsonUtil.parse(s, PlainTextBody.class);
    }

    if (BodyType.YOUTUBE_VIDEO.equals(body.getType())) {
      return jsonUtil.parse(s, YoutubeVideoBody.class);
    }

    throw new JsonProcessException(String.format("There is no question converter for %s type", body.getType()));
  }
}
