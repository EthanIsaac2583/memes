package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.mapper.ItemMapper;
import kz.ruanjian.memed.mapper.ItemMapperImpl;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.specifation.QuestionSpecification;
import kz.ruanjian.memed.util.Item;
import kz.ruanjian.memed.util.grader.GraderContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
  void findItem_shouldThrowNotFoundException_whenNumberNotPassedAndCanNotBeDetermined() {
    UUID visitId = dataGenerator.generateUUID();
    Long quizId = dataGenerator.generateLongId();
    Optional<Integer> number = Optional.empty();
    doReturn(Optional.empty()).when(questionRepository).findFirstAssessableQuestionNumber(quizId);

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> questionService.findItem(visitId, quizId, number));

    String expectedMessage = "Question not found";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(questionRepository).findFirstAssessableQuestionNumber(quizId);
  }

  @Test
  void findItem_shouldThrowNotFoundException_whenNumberResolvedAndQuestionNotExists() {
    UUID visitId = dataGenerator.generateUUID();
    Long quizId = dataGenerator.generateLongId();
    Optional<Integer> number = Optional.empty();
    long foundQuestionNumber = 2L;
    doReturn(Optional.of(foundQuestionNumber)).when(questionRepository).findFirstAssessableQuestionNumber(quizId);

    Pageable pageable = generateItemSearchPageable(foundQuestionNumber - 1L);
    Specification<Question> specification = generateQuestionSpecification(visitId, quizId);
    Page<Question> questionPage = new PageImpl<>(new ArrayList<>(), pageable, 100);
    doReturn(questionPage).when(questionRepository).findAll(specification, pageable);

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> questionService.findItem(visitId, quizId, number));

    String expectedMessage = "Question not found";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(questionRepository).findFirstAssessableQuestionNumber(quizId);
    verify(questionRepository).findAll(specification, pageable);
  }

  @Test
  void findItem_shouldReturnQuestionItem_whenNumberResolvedAndQuestionExists() {
    Question question = dataGenerator.generateQuestion();
    UUID visitId = question.getVisit().getId();
    Long quizId = question.getQuiz().getId();
    Optional<Integer> number = Optional.of(question.getNumber());

    Pageable pageable = generateItemSearchPageable(number.get() - 1L);
    Specification<Question> specification = generateQuestionSpecification(visitId, quizId);
    Page<Question> questionPage = new PageImpl<>(Collections.singletonList(question), pageable, 100);
    doReturn(questionPage).when(questionRepository).findAll(specification, pageable);

    Item<Question> expected = itemMapper.toItem(questionPage);
    Item<Question> actual = questionService.findItem(visitId, quizId, number);

    assertEquals(expected, actual);
  }

  @Test
  void provideAnswer_shouldThrow_whenNotExistingQuestionRequested() {
    UUID visitId = dataGenerator.generateUUID();
    Long quizId = dataGenerator.generateLongId();
    Long questionId = dataGenerator.generateLongId();
    doReturn(Optional.empty()).when(questionRepository).findByIdAndQuizIdAndVisitId(questionId, quizId, visitId);

    AnswerDto answerDto = new AnswerDto();
    answerDto.setAnswer(dataGenerator.generateSingleChoiceAnswer());

    NotFoundException thrown = assertThrows(NotFoundException.class,
      () -> questionService.provideAnswer(visitId, quizId, questionId, answerDto));

    String expectedMessage = "Question not found";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(questionRepository).findByIdAndQuizIdAndVisitId(questionId, quizId, visitId);
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

  private Pageable generateItemSearchPageable(Long number) {
    int size = 1;
    Sort sort = Sort.by(Sort.Order.asc("number"));

    return PageRequest.of(number.intValue(), size, sort);
  }

  private Specification<Question> generateQuestionSpecification(UUID visitId, Long quizId) {
    return QuestionSpecification.builder()
      .visitId(visitId)
      .quizId(quizId)
      .build();
  }
}
