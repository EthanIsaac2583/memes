package kz.ruanjian.memed.pojo.coverter;

import jakarta.persistence.AttributeConverter;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.json.PojoJson;
import org.springframework.stereotype.Component;

@Component
public class BodyConverter implements AttributeConverter<Body, String> {

  private final PojoJson pojoJson;

  public BodyConverter(PojoJson pojoJson) {
    this.pojoJson = pojoJson;
  }

  @Override
  public String convertToDatabaseColumn(Body body) {
    return pojoJson.stringify(body);
  }

  @Override
  public Body convertToEntityAttribute(String body) {
    return pojoJson.parseBody(body);
  }
}
