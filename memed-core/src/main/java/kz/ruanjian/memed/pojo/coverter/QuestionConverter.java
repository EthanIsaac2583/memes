package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.QuestionType;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.body.YouTubeVideoBody;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter implements AttributeConverter<Body, String> {

  private final PojoConverter pojoConverter;

  public QuestionConverter(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public String convertToDatabaseColumn(Body body) {
    if (body== null) {
      return null;
    }

    return pojoConverter.stringify(body);
  }

  @Override
  public Body convertToEntityAttribute(String s) {
    Body body = pojoConverter.convert(s, Body.class);

    if (body== null) {
      return null;
    }

    if (QuestionType.PLAIN_TEXT.equals(body.getType())) {
      return pojoConverter.convert(s, PlainTextBody.class);
    }

    if (QuestionType.YOUTUBE_VIDEO.equals(body.getType())) {
      return pojoConverter.convert(s, YouTubeVideoBody.class);
    }

    throw new PojoConvertException(String.format("There is no question converter for %s type", body.getType()));
  }
}
