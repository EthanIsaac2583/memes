package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.blank.MultipleChoiceBlank;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import org.springframework.stereotype.Component;

@Component
public class BlankConverter implements AttributeConverter<Blank, String> {

  private final PojoConverter pojoConverter;

  public BlankConverter(PojoConverter pojoConverter) {
    this.pojoConverter = pojoConverter;
  }

  @Override
  public String convertToDatabaseColumn(Blank blank) {
    if (blank == null) {
      return null;
    }

    return pojoConverter.stringify(blank);
  }

  @Override
  public Blank convertToEntityAttribute(String s) {
    Blank blank = pojoConverter.convert(s, Blank.class);

    if (blank == null) {
      return null;
    }

    if (BlankType.SINGLE_CHOICE.equals(blank.getType())) {
      return pojoConverter.convert(s, SingleChoiceBlank.class);
    }

    if (BlankType.MULTIPLE_CHOICE.equals(blank.getType())) {
      return pojoConverter.convert(s, MultipleChoiceBlank.class);
    }

    throw new PojoConvertException(String.format("There is no blank converter for %s type", blank.getType()));
  }
}
