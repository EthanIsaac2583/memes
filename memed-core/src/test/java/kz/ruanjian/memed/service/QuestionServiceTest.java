package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.mapper.ItemMapper;
import kz.ruanjian.memed.mapper.ItemMapperImpl;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.util.grader.GraderContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

  @Mock
  QuestionRepository questionRepository;

  @Mock
  GraderContext graderContext;

  @Spy
  ItemMapper itemMapper = new ItemMapperImpl();

  @InjectMocks
  QuestionService questionService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findItem() {
  }

  @Test
  void provideAnswer_shouldProvideAnswer_whenExistingQuestionRequestedAndValidAnswerPassed() {
    Question expected = dataGenerator.generateQuestion();
    UUID visitId = expected.getVisit().getId();
    Long quizId = expected.getQuiz().getId();
    Long questionId = expected.getId();
    doReturn(Optional.of(expected)).when(questionRepository).findByIdAndQuizIdAndVisitId(questionId, quizId, visitId);

    AnswerDto answerDto = new AnswerDto();
    answerDto.setAnswer(expected.getAnswer());
    doReturn(expected).when(questionRepository).save(expected);

    Question actual = questionService.provideAnswer(visitId, quizId, questionId, answerDto);

    assertEquals(expected, actual);

    verify(questionRepository).findByIdAndQuizIdAndVisitId(questionId, quizId, visitId);
    verify(questionRepository).save(expected);
  }

  @Test
  void provideAnswer_should_when2() {
  }
}
