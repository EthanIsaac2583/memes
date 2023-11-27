package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.json.PojoJson;
import kz.ruanjian.memed.pojo.body.Body;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BodyDeserializer extends JsonDeserializer<Body> {

  private final PojoJson pojoJson;

  public BodyDeserializer(PojoJson pojoJson) {
    this.pojoJson = pojoJson;
  }

  @Override
  public Body deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String stringedQuestion = jsonParser.getCodec().readTree(jsonParser).toString();

    return pojoJson.parseBody(stringedQuestion);
  }
}
