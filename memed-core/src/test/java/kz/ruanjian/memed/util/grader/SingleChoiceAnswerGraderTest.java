package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.pojo.answer.MultipleChoiceAnswer;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.util.data.DataGenerator;
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
    Question question = new Question();
    MultipleChoiceAnswer answer = dataGenerator.generateMultipleChoiceAnswer();
    question.setAnswer(answer);

    String expectedMessage = "Single choice answer required";
    GraderMismatchException thrown = assertThrows(GraderMismatchException.class, () -> singleChoiceAnswerGrader.grade(question));
    assertEquals(expectedMessage, thrown.getMessage());
  }

  @Test
  void grade_shouldReturnGradeMin_whenWrongAnswerPassed() {
    SingleChoiceAnswer answer = dataGenerator.generateSingleChoiceAnswer();
    Task task = new Task();
    task.setAnswer(answer);
    Question question = new Question();
    question.setAnswer(answer);
    question.setTask(task);

    int expected = memedProperties.getApplication().getGradeMax();
    int actual = singleChoiceAnswerGrader.grade(question);

    assertEquals(expected, actual);
  }

  @Test
  void grade_shouldReturnGradeMax_whenCorrectAnswerPassed() {
    Task task = new Task();
    task.setAnswer(dataGenerator.generateSingleChoiceAnswer());
    Question question = new Question();
    question.setAnswer(dataGenerator.generateSingleChoiceAnswer());
    question.setTask(task);

    int expected = memedProperties.getApplication().getGradeMin();
    int actual = singleChoiceAnswerGrader.grade(question);

    assertEquals(expected, actual);
  }
}
