package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.pojo.coverter.PojoConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AnswerDeserializer extends JsonDeserializer<Answer> {

  private final PojoConverter pojoConverter;

  public AnswerDeserializer(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public Answer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String stringedAnswer = jsonParser.getCodec().readTree(jsonParser).toString();
    Answer answer = pojoConverter.convert(stringedAnswer, Answer.class);

    if (answer == null) {
      return null;
    }

    if (BlankType.SINGLE_CHOICE.equals(answer.getType())) {
      return pojoConverter.convert(stringedAnswer, SingleChoiceAnswer.class);
    }

    throw new PojoDeserializeException(String.format("There is no answer deserializer for %s type", answer.getType()));
  }
}
