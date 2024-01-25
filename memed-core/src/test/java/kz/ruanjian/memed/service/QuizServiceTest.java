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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

  @Spy
  QuizGenerator quizGenerator = new QuizGenerator();

  @Mock
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
  void findAllByVisitId() {
  }

  @Test
  void requestByTemplateIdAndVisitId() {
  }

  @Test
  void finalizeByIdAndVisitId() {
  }
}
