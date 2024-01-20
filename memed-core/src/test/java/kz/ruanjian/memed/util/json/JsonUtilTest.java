package kz.ruanjian.memed.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.answer.MultipleChoiceAnswer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.HashSet;

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
  void parse_shouldThrowJsonUtilProcessingException_whenUnprocessableStringPassed() {
    String invalidJsonString = "'John' - Wrong quotes";
    assertThrows(JsonUtilProcessingException.class, () -> jsonUtil.parse(invalidJsonString, Object.class));
  }

  @Test
  void parse_shouldReturnMultipleChoiceAnswer_whenValidStringPassed() throws JsonProcessingException {
    MultipleChoiceAnswer expected = MultipleChoiceAnswer.builder()
      .type(BlankType.MULTIPLE_CHOICE)
      .keys(new HashSet<>(Arrays.asList("One", "Two")))
      .build();

    MultipleChoiceAnswer actual = jsonUtil.parse(asString(expected), MultipleChoiceAnswer.class);

    assertEquals(expected, actual);
  }

  private String asString(Object value) throws JsonProcessingException {
    return objectMapper.writeValueAsString(value);
  }
}
