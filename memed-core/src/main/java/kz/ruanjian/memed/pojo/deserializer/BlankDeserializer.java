package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import kz.ruanjian.memed.pojo.coverter.PojoConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BlankDeserializer extends JsonDeserializer<Blank> {

  private final PojoConverter pojoConverter;

  public BlankDeserializer(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public Blank deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String stringedBlank = jsonParser.getCodec().readTree(jsonParser).toString();
    Blank blank = pojoConverter.convert(stringedBlank, Blank.class);

    if (blank == null) {
      return null;
    }

    if (BlankType.SINGLE_CHOICE.equals(blank.getType())) {
      return pojoConverter.convert(stringedBlank, SingleChoiceBlank.class);
    }

    throw new PojoDeserializeException(String.format("There is no blank deserializer for %s type", blank.getType()));
  }
}
