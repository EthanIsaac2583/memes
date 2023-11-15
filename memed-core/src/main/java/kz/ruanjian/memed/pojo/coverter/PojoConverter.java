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
    if (value == null) {
      return null;
    }

    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }


  public  <T> T convert(Object value, Class<T> valueType) {
    if (value == null) {
      return null;
    }

    try {
      return objectMapper.readValue(stringify(value), valueType);
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }

  public  <T> T convert(String value, Class<T> valueType) {
    if (value == null) {
      return null;
    }

    try {
      return objectMapper.readValue(value, valueType);
    } catch (JsonProcessingException e) {
      throw new PojoConvertException(e);
    }
  }
}
