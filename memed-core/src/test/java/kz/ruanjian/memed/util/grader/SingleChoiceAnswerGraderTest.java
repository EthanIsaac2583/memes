package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.config.MemedProperties;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SingleChoiceAnswerGraderTest {

  @Spy
  MemedProperties memedProperties;

  @InjectMocks
  SingleChoiceAnswerGrader singleChoiceAnswerGrader;

  @BeforeEach
  void setUp() {
    MemedProperties.ApplicationProperties applicationProperties = new MemedProperties.ApplicationProperties();
    applicationProperties.setGradeMax(100);
    applicationProperties.setGradeMin(0);
    memedProperties.setApplication(applicationProperties);
  }

  @Test
  void grade_shouldThrowGraderTypeMismatchException_whenAnotherQuestionPassed() {
    Question question = new Question();
    SingleChoiceAnswer answer = SingleChoiceAnswer.builder()
      .type(BlankType.SINGLE_CHOICE)
      .key("One")
      .build();
  }

  @Test
  void grade_shouldReturnGradeMin_whenWrongAnswerPassed() {
  }

  @Test
  void grade_shouldReturnGradeMax_whenCorrectAnswerPassed() {
  }
}
