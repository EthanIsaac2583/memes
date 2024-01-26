package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.config.AbstractRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Sql(value = "classpath:db/question/initial.sql")
class QuestionRepositoryTest extends AbstractRepositoryTest {

  @Autowired
  QuestionRepository questionRepository;

  @Test
  void findFirstAssessableQuestionNumber1() {
    Optional<Long> expected = Optional.of(1L);
    Optional<Long> actual = questionRepository.findFirstAssessableQuestionNumber(1L);

    assertEquals(expected, actual);
  }

  @Test
  void findFirstAssessableQuestionNumber2() {
    Optional<Long> expected = Optional.empty();
    Optional<Long> actual = questionRepository.findFirstAssessableQuestionNumber(2L);

    assertEquals(expected, actual);
  }
}
