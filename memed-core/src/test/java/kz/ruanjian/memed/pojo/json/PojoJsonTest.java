package kz.ruanjian.memed.pojo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.util.json.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PojoJsonTest {

  @Mock
  JsonUtil jsonUtil;

  @InjectMocks
  PojoJson pojoJson;

  ObjectMapper objectMapper;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    dataGenerator = new DataGenerator();
  }

  @Test
  void stringify_shouldDoAppropriateOperations_whenInvoked() throws JsonProcessingException {
    SingleChoiceAnswer answer = dataGenerator.generateSingleChoiceAnswer();

    String expected = asString(answer);
    doReturn(expected).when(jsonUtil).stringify(answer);

    String actual = pojoJson.stringify(answer);

    assertEquals(expected, actual);
    verify(jsonUtil).stringify(answer);
  }

  @Test
  void parseAnswer() {
  }

  @Test
  void parseBlank() {
  }

  @Test
  void parseBody() {
  }

  private String asString(Object value) throws JsonProcessingException {
    return objectMapper.writeValueAsString(value);
  }
}
