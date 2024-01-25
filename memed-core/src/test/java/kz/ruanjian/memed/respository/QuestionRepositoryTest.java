package kz.ruanjian.memed.respository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuestionRepositoryTest {

  @Autowired
  QuestionRepository questionRepository;

  @Test
  void findFirstAssessableQuestionNumber() {
  }
}
