package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.respository.QuizRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.util.generator.QuizGenerator;
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
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

  @Spy
  QuizGenerator quizGenerator = new QuizGenerator();

  @Spy
  QuizRepository quizRepository;

  @Mock
  TemplateService templateService;

  @Mock
  VisitService visitService;

  @InjectMocks
  QuizService quizService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findByIdAndVisitId_shouldThrowNotFoundException_whenQuizNotExists() {
    UUID visitId = dataGenerator.generateUUID();
    Long id = dataGenerator.generateLongId();
    doReturn(Optional.empty()).when(quizRepository).findByIdAndVisitId(id, visitId);

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> quizService.findByIdAndVisitId(id, visitId));

    String expectedMessage = "Quiz not found";
    assertEquals(expectedMessage, thrown.getMessage());
  }

  @Test
  void findByIdAndVisitId_shouldReturnQuiz_whenQuizExists() {
    Quiz expected = dataGenerator.generateQuiz();
    Long quizId = expected.getId();
    UUID visitId = expected.getVisit().getId();
    doReturn(Optional.of(expected)).when(quizRepository).findByIdAndVisitId(quizId, visitId);

    Quiz actual = quizService.findByIdAndVisitId(quizId, visitId);

    assertEquals(expected, actual);
    verify(quizRepository).findByIdAndVisitId(quizId, visitId);
  }

  @Test
  void findAllByVisitId_shouldReturnQuizPage_whenRequested() {
    UUID visitId = dataGenerator.generateUUID();
    List<Quiz> quizzes = dataGenerator.generateQuizzes(10);
    Pageable pageable = PageRequest.of(2, 10);
    Page<Quiz> expected = new PageImpl<>(quizzes, pageable, 123);
    Specification<Quiz> specification = quizRepository.visitIdEquals(visitId);
    doReturn(specification).when(quizRepository).visitIdEquals(visitId);
    doReturn(expected).when(quizRepository).findAll(specification, pageable);

    Page<Quiz> actual = quizService.findAllByVisitId(pageable, visitId);

    assertEquals(expected, actual);
    verify(quizRepository).findAll(specification, pageable);
  }

  @Test
  void requestByTemplateIdAndVisitId() {
  }

  @Test
  void finalizeByIdAndVisitId() {
  }
}
