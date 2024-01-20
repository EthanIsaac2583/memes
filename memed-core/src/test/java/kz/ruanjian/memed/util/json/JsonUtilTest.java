package kz.ruanjian.memed.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = ObjectMapper.class)
class JsonUtilTest {

  @Spy
  ObjectMapper objectMapper;

  @InjectMocks
  JsonUtil jsonUtil;

  @Test
  void stringify() {
  }

  @Test
  void parse_shouldReturnNull_whenNullStringPassed() {
    Object actual = jsonUtil.parse("null", Object.class);

    assertNull(actual);
  }

  @Test
  void parse_shouldReturn_when() {

  }
}
