package kz.ruanjian.memed.pojo.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.ruanjian.memed.pojo.QuestionType;
import kz.ruanjian.memed.pojo.coverter.PojoConverter;
import kz.ruanjian.memed.pojo.quiestion.PlainTextQuestion;
import kz.ruanjian.memed.pojo.quiestion.Question;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QuestionDeserializer extends JsonDeserializer<Question> {

  private final PojoConverter pojoConverter;

  public QuestionDeserializer(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public Question deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
    Question question = pojoConverter.convert(jsonParser, Question.class);

    if (question == null) {
      return null;
    }

    if (QuestionType.PLAIN_TEXT.equals(question.getType())) {
      return pojoConverter.convert(jsonParser, PlainTextQuestion.class);
    }

    throw new PojoDeserializeException(String.format("There is no question deserializer for %s type", question.getType()));
  }
}
