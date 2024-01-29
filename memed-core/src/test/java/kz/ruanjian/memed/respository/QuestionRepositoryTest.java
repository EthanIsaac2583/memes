package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.config.AbstractRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Sql(value = "classpath:db/question/initial.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class QuestionRepositoryTest extends AbstractRepositoryTest {

  @Autowired
  QuestionRepository questionRepository;

  @Test
  void findFirstAssessableQuestionNumber_shouldReturnNumber_whenNotAssessedQuestionExists() {
    Optional<Long> expected = Optional.of(1L);
    Optional<Long> actual = questionRepository.findFirstAssessableQuestionNumber(1L);

    assertEquals(expected, actual);
  }

  @Test
  void findFirstAssessableQuestionNumber_shouldReturnEmptyOptional_whenNotAssessedQuestionNotExists() {
    Optional<Long> expected = Optional.empty();
    Optional<Long> actual = questionRepository.findFirstAssessableQuestionNumber(2L);

    assertEquals(expected, actual);
  }
}
