package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.BodyType;
import kz.ruanjian.memed.pojo.coverter.PojoConverter;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.body.YoutubeVideoBody;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BodyDeserializer extends JsonDeserializer<Body> {

  private final PojoConverter pojoConverter;

  public BodyDeserializer(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public Body deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String stringedQuestion = jsonParser.getCodec().readTree(jsonParser).toString();
    Body body = pojoConverter.convert(stringedQuestion, Body.class);

    if (body== null) {
      return null;
    }

    if (BodyType.PLAIN_TEXT.equals(body.getType())) {
      return pojoConverter.convert(stringedQuestion, PlainTextBody.class);
    }

    if (BodyType.YOUTUBE_VIDEO.equals(body.getType())) {
      return pojoConverter.convert(stringedQuestion, YoutubeVideoBody.class);
    }

    throw new PojoDeserializeException(String.format("There is no question deserializer for %s type", body.getType()));
  }
}
