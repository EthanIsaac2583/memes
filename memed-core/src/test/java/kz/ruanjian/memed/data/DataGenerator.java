package kz.ruanjian.memed.data;

import com.github.javafaker.Faker;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.BodyType;
import kz.ruanjian.memed.pojo.Option;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import kz.ruanjian.memed.pojo.body.PlainTextBody;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

  private final Faker faker = new Faker();

  public SingleChoiceAnswer generateSingleChoiceAnswer() {
    return SingleChoiceAnswer.builder()
      .type(BlankType.SINGLE_CHOICE)
      .key(generateWord(5))
      .build();
  }

  public SingleChoiceBlank generateSingleChoiceBlank() {
    return SingleChoiceBlank.builder()
      .type(BlankType.SINGLE_CHOICE)
      .options(generateOptions(3))
      .build();
  }

  public PlainTextBody generatePlainTextBody() {
    return PlainTextBody.builder()
      .type(BodyType.PLAIN_TEXT)
      .text(generateSentence(3))
      .build();
  }

  private String generateWord(int size) {
    return faker.lorem()
      .fixedString(size)
      .replaceAll(" ", "_");
  }

  private String generateSentence(int words) {
    return faker.lorem()
      .sentence(words);
  }

  private Option generateOption() {
    Option option = new Option();

    option.setKey(generateWord(4));
    option.setValue(generateWord(10));

    return option;
  }

  private Set<Option> generateOptions(int size) {
    return Stream.generate(this::generateOption)
      .limit(size)
      .collect(Collectors.toSet());
  }
}
