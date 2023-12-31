package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.json.PojoJson;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter implements AttributeConverter<Answer, String> {

  private final PojoJson pojoJson;

  public AnswerConverter(PojoJson pojoJson) {
    this.pojoJson = pojoJson;
  }

  @Override
  public String convertToDatabaseColumn(Answer answer) {
    return pojoJson.stringify(answer);
  }

  @Override
  public Answer convertToEntityAttribute(String answer) {
    return pojoJson.parseAnswer(answer);
  }
}
