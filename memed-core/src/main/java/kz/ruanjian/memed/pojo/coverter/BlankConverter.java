package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.json.PojoJson;
import org.springframework.stereotype.Component;

@Component
public class BlankConverter implements AttributeConverter<Blank, String> {

  private final PojoJson pojoJson;

  public BlankConverter(PojoJson pojoJson) {
    this.pojoJson = pojoJson;
  }

  @Override
  public String convertToDatabaseColumn(Blank blank) {
    return pojoJson.stringify(blank);
  }

  @Override
  public Blank convertToEntityAttribute(String blank) {
    return pojoJson.parseBlank(blank);
  }
}
