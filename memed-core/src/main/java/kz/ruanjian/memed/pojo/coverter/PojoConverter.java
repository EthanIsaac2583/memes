package kz.ruanjian.memed.pojo.coverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class PojoConverter {

  private final ObjectMapper objectMapper;

  public PojoConverter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String stringify(Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }


  public  <T> T convert(Object value, Class<T> valueType) {
    try {
      return objectMapper.readValue(stringify(value), valueType);
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }
}
