package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter implements AttributeConverter<Answer, String> {

  private final PojoConverter pojoConverter;

  public AnswerConverter(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public String convertToDatabaseColumn(Answer answer) {
    return pojoConverter.stringify(answer);
  }

  @Override
  public Answer convertToEntityAttribute(String s) {
    Answer answer = pojoConverter.convert(s, Answer.class);

    if (BlankType.SINGLE_CHOICE.equals(answer.getType())) {
      return pojoConverter.convert(s, SingleChoiceAnswer.class);
    }

    throw new PojoConvertException(String.format("There is no answer converter for %s type", answer.getType()));
  }
}
