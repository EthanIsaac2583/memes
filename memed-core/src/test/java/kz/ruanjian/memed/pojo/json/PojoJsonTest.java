package kz.ruanjian.memed.pojo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import kz.ruanjian.memed.pojo.body.Body;
import kz.ruanjian.memed.pojo.body.ImageBody;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
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
  void parseAnswer_shouldReturnNull_whenNullAnswerPassed() throws JsonProcessingException {
    String answerString = asString(null);
    doReturn(null).when(jsonUtil).parse(answerString, Answer.class);

    Answer actual = pojoJson.parseAnswer(answerString);

    assertNull(actual);
    verify(jsonUtil).parse(answerString, Answer.class);
  }

  @Test
  void parseAnswer_shouldThrowPojoProcessException_whenUnknownAnswerStringPassed() throws JsonProcessingException {
    Answer answer = new Answer();
    String answerString = asString(answer);
    doReturn(answer).when(jsonUtil).parse(answerString, Answer.class);

    PojoProcessException thrown = assertThrows(PojoProcessException.class, () -> pojoJson.parseAnswer(answerString));

    String exceptionMessage = "Unknown answer to parse";
    assertEquals(exceptionMessage, thrown.getMessage());

    verify(jsonUtil).parse(answerString, Answer.class);
  }

  @Test
  void parseAnswer_shouldReturnSingleChoiceAnswer_whenValidAnswerStringPassed() throws JsonProcessingException {
    Answer expected = dataGenerator.generateSingleChoiceAnswer();
    String answerString = asString(expected);
    doReturn(expected).when(jsonUtil).parse(answerString, Answer.class);
    doReturn(expected).when(jsonUtil).parse(answerString, SingleChoiceAnswer.class);

    Answer actual = pojoJson.parseAnswer(answerString);

    assertEquals(expected, actual);
    verify(jsonUtil).parse(answerString, Answer.class);
    verify(jsonUtil).parse(answerString, SingleChoiceAnswer.class);
  }

  @Test
  void parseBlank_shouldReturnNull_whenNullBlankPassed() throws JsonProcessingException {
    String blankString = asString(null);
    doReturn(null).when(jsonUtil).parse(blankString, Blank.class);

    Blank actual = pojoJson.parseBlank(blankString);

    assertNull(actual);
    verify(jsonUtil).parse(blankString, Blank.class);
  }

  @Test
  void parseBlank_shouldThrowPojoProcessException_whenUnknownBlankStringPassed() throws JsonProcessingException {
    Blank blank = new Blank();
    String blankString = asString(blank);
    doReturn(blank).when(jsonUtil).parse(blankString, Blank.class);

    PojoProcessException thrown = assertThrows(PojoProcessException.class, () -> pojoJson.parseBlank(blankString));

    String expectedMessage = "Unknown blank to parse";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(jsonUtil).parse(blankString, Blank.class);
  }

  @Test
  void parseBlank_shouldReturnSingleChoiceBlank_whenValidBlankStringPassed() throws JsonProcessingException {
    Blank expected = dataGenerator.generateSingleChoiceBlank();
    String blankString = asString(expected);
    doReturn(expected).when(jsonUtil).parse(blankString, Blank.class);
    doReturn(expected).when(jsonUtil).parse(blankString, SingleChoiceBlank.class);

    Blank actual = pojoJson.parseBlank(blankString);

    assertEquals(expected, actual);
    verify(jsonUtil).parse(blankString, Blank.class);
    verify(jsonUtil).parse(blankString, SingleChoiceBlank.class);
  }

  @Test
  void parseBody_shouldReturnNull_whenNullBodyPassed() throws JsonProcessingException {
    String bodyString = asString(null);
    doReturn(null).when(jsonUtil).parse(bodyString, Body.class);

    Body actual = pojoJson.parseBody(bodyString);

    assertNull(actual);
    verify(jsonUtil).parse(bodyString, Body.class);
  }

  @Test
  void parseBody_shouldThrowPojoProcessException_whenUnknownBodyStringPassed() throws JsonProcessingException {
    Body body = new Body();
    String bodyString = asString(body);
    doReturn(body).when(jsonUtil).parse(bodyString, Body.class);

    PojoProcessException thrown = assertThrows(PojoProcessException.class, () -> pojoJson.parseBody(bodyString));

    String expectedMessage = "Unknown body to parse";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(jsonUtil).parse(bodyString, Body.class);
  }

  @Test
  void parseBody_shouldReturnPlainTextBody_whenValidBodyStringPassed() throws JsonProcessingException {
    Body expected = dataGenerator.generatePlainTextBody();
    String bodyString = asString(expected);
    doReturn(expected).when(jsonUtil).parse(bodyString, Body.class);
    doReturn(expected).when(jsonUtil).parse(bodyString, PlainTextBody.class);

    Body actual = pojoJson.parseBody(bodyString);

    assertEquals(expected, actual);
    verify(jsonUtil).parse(bodyString, Body.class);
    verify(jsonUtil).parse(bodyString, PlainTextBody.class);
  }

  @Test
  void parseBody_shouldReturnImageBody_whenValidBodyStringPassed() throws JsonProcessingException {
    Body expected = dataGenerator.generateImageBody();
    String bodyString = asString(expected);
    doReturn(expected).when(jsonUtil).parse(bodyString, Body.class);
    doReturn(expected).when(jsonUtil).parse(bodyString, ImageBody.class);

    Body actual = pojoJson.parseBody(bodyString);

    assertEquals(expected, actual);
    verify(jsonUtil).parse(bodyString, Body.class);
    verify(jsonUtil).parse(bodyString, ImageBody.class);
  }

  @Test
  void parseBody_should_when2() {
  }

  private String asString(Object value) throws JsonProcessingException {
    return objectMapper.writeValueAsString(value);
  }
}
