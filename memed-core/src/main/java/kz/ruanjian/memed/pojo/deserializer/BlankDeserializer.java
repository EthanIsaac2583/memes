package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.json.PojoJson;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BlankDeserializer extends JsonDeserializer<Blank> {

  private final PojoJson pojoJson;

  public BlankDeserializer(PojoJson pojoJson) {
    this.pojoJson = pojoJson;
  }

  @Override
  public Blank deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String stringedBlank = jsonParser.getCodec().readTree(jsonParser).toString();

    return pojoJson.parseBlank(stringedBlank);
  }
}
