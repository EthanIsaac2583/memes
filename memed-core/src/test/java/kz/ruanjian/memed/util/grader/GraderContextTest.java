package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.data.DataGenerator;
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
class GraderContextTest {

  @Mock
  SingleChoiceAnswerGrader singleChoiceAnswerGrader;

  @InjectMocks
  GraderContext graderContext;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void grade_shouldThrowGraderException_whenNoMatchingGrader() {
    Question question = Question.builder()
      .answer(dataGenerator.generateMultipleChoiceAnswer())
      .build();

    GraderException thrown = assertThrows(GraderException.class, () -> graderContext.grade(question));

    String expectedMessage = "There is no grader to handle question";
    assertEquals(expectedMessage, thrown.getMessage());
  }

  @Test
  void grade_shouldDoAppropriateOperations_whenQuestionWithSingleChoiceAnswerPassed() {
    Question question = Question.builder()
      .answer(dataGenerator.generateSingleChoiceAnswer())
      .build();

    int expected = 100;
    doReturn(expected).when(singleChoiceAnswerGrader).grade(question);

    int actual = graderContext.grade(question);

    assertEquals(expected, actual);
    verify(singleChoiceAnswerGrader).grade(question);
  }
}
