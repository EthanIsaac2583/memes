package kz.ruanjian.memed.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

  private final ObjectMapper objectMapper;

  public JsonUtil(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String stringify(Object value) {
    if (value == null) {
      return null;
    }

    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new JsonUtilProcessingException(e);
    }
  }

  public  <T> T parse(String value, Class<T> valueType) {
    if (value == null) {
      return null;
    }

    try {
      return objectMapper.readValue(value, valueType);
    } catch (JsonProcessingException e) {
      throw new JsonUtilProcessingException(e);
    }
  }
}
