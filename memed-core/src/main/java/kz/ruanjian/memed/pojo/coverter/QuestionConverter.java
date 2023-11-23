package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.QuestionType;
import kz.ruanjian.memed.pojo.quiestion.PlainTextQuestion;
import kz.ruanjian.memed.pojo.quiestion.Question;
import kz.ruanjian.memed.pojo.quiestion.YouTubeVideoQuestion;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter implements AttributeConverter<Question, String> {

  private final PojoConverter pojoConverter;

  public QuestionConverter(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public String convertToDatabaseColumn(Question question) {
    if (question == null) {
      return null;
    }

    return pojoConverter.stringify(question);
  }

  @Override
  public Question convertToEntityAttribute(String s) {
    Question question = pojoConverter.convert(s, Question.class);

    if (question == null) {
      return null;
    }

    if (QuestionType.PLAIN_TEXT.equals(question.getType())) {
      return pojoConverter.convert(s, PlainTextQuestion.class);
    }

    if (QuestionType.YOUTUBE_VIDEO.equals(question.getType())) {
      return pojoConverter.convert(s, YouTubeVideoQuestion.class);
    }

    throw new PojoConvertException(String.format("There is no question converter for %s type", question.getType()));
  }
}
