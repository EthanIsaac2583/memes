package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.json.PojoJson;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AnswerDeserializer extends JsonDeserializer<Answer> {

  private final PojoJson pojoJson;

  public AnswerDeserializer(PojoJson pojoJson) {
    this.pojoJson = pojoJson;
  }

  @Override
  public Answer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String answer = jsonParser.getCodec().readTree(jsonParser).toString();

    return pojoJson.parseAnswer(answer);
  }
}
