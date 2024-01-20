package kz.ruanjian.memed.data;

import com.github.javafaker.Faker;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.answer.MultipleChoiceAnswer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;

import java.util.Arrays;
import java.util.HashSet;

public class DataGenerator {

  private final Faker faker = new Faker();

  public SingleChoiceAnswer generateSingleChoiceAnswer() {
    return SingleChoiceAnswer.builder()
      .type(BlankType.SINGLE_CHOICE)
      .key(generateWord(5))
      .build();
  }

  public MultipleChoiceAnswer generateMultipleChoiceAnswer() {
    return MultipleChoiceAnswer.builder()
      .type(BlankType.MULTIPLE_CHOICE)
      .keys(new HashSet<>(Arrays.asList(generateWord(3), generateWord(3))))
      .build();
  }

  private String generateWord(int size) {
    return faker.lorem()
      .fixedString(size)
      .replaceAll(" ", "_");
  }
}
