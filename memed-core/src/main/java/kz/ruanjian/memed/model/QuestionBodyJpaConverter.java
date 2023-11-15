package kz.ruanjian.memed.model;

import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class QuestionBodyJpaConverter implements AttributeConverter<QuestionBody, String> {

//  private final

  @Override
  public String convertToDatabaseColumn(QuestionBody questionBody) {
    return null;
  }

  @Override
  public QuestionBody convertToEntityAttribute(String s) {
    return null;
  }
}
