package kz.ruanjian.memed.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
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

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void stringify_shouldReturnNull_whenNullPassed() {
    Object actual = jsonUtil.stringify(null);

    assertNull(actual);
  }

  @Test
  void stringify_shouldReturnValidString_whenSingleChoiceAnswerPassed() throws JsonProcessingException {
    SingleChoiceAnswer answer = dataGenerator.generateSingleChoiceAnswer();

    String expected = asString(answer);
    String actual = jsonUtil.stringify(answer);

    assertEquals(expected, actual);
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
  void parse_shouldReturnSingleChoiceAnswer_whenValidStringPassed() throws JsonProcessingException {
    SingleChoiceAnswer expected = dataGenerator.generateSingleChoiceAnswer();

    SingleChoiceAnswer actual = jsonUtil.parse(asString(expected), SingleChoiceAnswer.class);

    assertEquals(expected, actual);
  }

  private String asString(Object value) throws JsonProcessingException {
    return objectMapper.writeValueAsString(value);
  }
}
