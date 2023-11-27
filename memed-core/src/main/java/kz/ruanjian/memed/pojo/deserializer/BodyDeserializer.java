package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.BodyType;
import kz.ruanjian.memed.util.json.JsonUtil;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.body.YoutubeVideoBody;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BodyDeserializer extends JsonDeserializer<Body> {

  private final JsonUtil jsonUtil;

  public BodyDeserializer(JsonUtil jsonUtil) {
    this.jsonUtil = jsonUtil;
  }

  @Override
  public Body deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String stringedQuestion = jsonParser.getCodec().readTree(jsonParser).toString();
    Body body = jsonUtil.parse(stringedQuestion, Body.class);

    if (body== null) {
      return null;
    }

    if (BodyType.PLAIN_TEXT.equals(body.getType())) {
      return jsonUtil.parse(stringedQuestion, PlainTextBody.class);
    }

    if (BodyType.YOUTUBE_VIDEO.equals(body.getType())) {
      return jsonUtil.parse(stringedQuestion, YoutubeVideoBody.class);
    }

    throw new PojoDeserializeException(String.format("There is no question deserializer for %s type", body.getType()));
  }
}
