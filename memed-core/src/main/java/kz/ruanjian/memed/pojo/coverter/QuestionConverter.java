package kz.ruanjian.memed.pojo.coverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.QuestionType;
import kz.ruanjian.memed.pojo.quiestion.PlainTextQuestion;
import kz.ruanjian.memed.pojo.quiestion.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter implements AttributeConverter<Question, String> {

  private final ObjectMapper objectMapper;

  public QuestionConverter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public String convertToDatabaseColumn(Question question) {
    try {
      return objectMapper.writeValueAsString(question);
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }

  @Override
  public Question convertToEntityAttribute(String s) {
    try {
      Question question = objectMapper.readValue(s, Question.class);

      if (QuestionType.PLAIN_TEXT.equals(question.getType())) {
        return objectMapper.readValue(s, PlainTextQuestion.class);
      }

      throw new PojoConvertException(String.format("There is no pojo converter for %s type", question.getType()));
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }
}
