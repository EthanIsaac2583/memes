package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SingleChoiceAnswerGraderTest {

  @Spy
  MemedProperties memedProperties;

  @InjectMocks
  SingleChoiceAnswerGrader singleChoiceAnswerGrader;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    MemedProperties.ApplicationProperties applicationProperties = new MemedProperties.ApplicationProperties();
    applicationProperties.setGradeMax(100);
    applicationProperties.setGradeMin(0);
    memedProperties.setApplication(applicationProperties);

    dataGenerator = new DataGenerator();
  }

  @Test
  void grade_shouldThrowGraderTypeMismatchException_whenAnotherQuestionPassed() {
    Question question = Question.builder()
      .answer(dataGenerator.generateMultipleChoiceAnswer())
      .build();

    String expectedMessage = "Single choice answer required";
    GraderMismatchException thrown = assertThrows(GraderMismatchException.class, () -> singleChoiceAnswerGrader.grade(question));
    assertEquals(expectedMessage, thrown.getMessage());
  }

  @Test
  void grade_shouldReturnGradeMin_whenWrongAnswerPassed() {
    SingleChoiceAnswer answer = dataGenerator.generateSingleChoiceAnswer();
    Question question = Question.builder()
      .answer(answer)
      .task(Task.builder()
        .answer(answer)
        .build())
      .build();

    int expected = memedProperties.getApplication().getGradeMax();
    int actual = singleChoiceAnswerGrader.grade(question);

    assertEquals(expected, actual);
  }

  @Test
  void grade_shouldReturnGradeMax_whenCorrectAnswerPassed() {
    Question question = Question.builder()
      .answer(dataGenerator.generateSingleChoiceAnswer())
      .task(Task.builder()
        .answer(dataGenerator.generateSingleChoiceAnswer())
        .build())
      .build();

    int expected = memedProperties.getApplication().getGradeMin();
    int actual = singleChoiceAnswerGrader.grade(question);

    assertEquals(expected, actual);
  }
}
