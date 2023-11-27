package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.blank.MultipleChoiceBlank;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import kz.ruanjian.memed.util.json.JsonUtil;
import kz.ruanjian.memed.util.json.JsonProcessException;
import org.springframework.stereotype.Component;

@Component
public class BlankConverter implements AttributeConverter<Blank, String> {

  private final JsonUtil jsonUtil;

  public BlankConverter(JsonUtil jsonUtil) {
    this.jsonUtil = jsonUtil;
  }

  @Override
  public String convertToDatabaseColumn(Blank blank) {
    if (blank == null) {
      return null;
    }

    return jsonUtil.stringify(blank);
  }

  @Override
  public Blank convertToEntityAttribute(String s) {
    Blank blank = jsonUtil.parse(s, Blank.class);

    if (blank == null) {
      return null;
    }

    if (BlankType.SINGLE_CHOICE.equals(blank.getType())) {
      return jsonUtil.parse(s, SingleChoiceBlank.class);
    }

    if (BlankType.MULTIPLE_CHOICE.equals(blank.getType())) {
      return jsonUtil.parse(s, MultipleChoiceBlank.class);
    }

    throw new JsonProcessException(String.format("There is no blank converter for %s type", blank.getType()));
  }
}
