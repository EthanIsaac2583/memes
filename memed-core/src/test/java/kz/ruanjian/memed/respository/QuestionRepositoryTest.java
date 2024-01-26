package kz.ruanjian.memed.respository;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ruanjian.memed.config.PrepareDbTestExecutionListener;
import kz.ruanjian.memed.pojo.coverter.AnswerConverter;
import kz.ruanjian.memed.pojo.json.PojoJson;
import kz.ruanjian.memed.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(value = {ObjectMapper.class, JsonUtil.class, PojoJson.class, AnswerConverter.class})
@TestExecutionListeners(value = {PrepareDbTestExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class QuestionRepositoryTest {

  @Autowired
  QuestionRepository questionRepository;

  @Test
  @Sql(value = "classpath:db/question/initial.sql")
  void findFirstAssessableQuestionNumber1() {
    Optional<Long> expected = Optional.of(1L);
    Optional<Long> actual = questionRepository.findFirstAssessableQuestionNumber(1L);

    assertEquals(expected, actual);
  }

  @Test
  @Sql(value = "classpath:db/question/initial.sql")
  void findFirstAssessableQuestionNumber2() {
    Optional<Long> expected = Optional.empty();
    Optional<Long> actual = questionRepository.findFirstAssessableQuestionNumber(2L);

    assertEquals(expected, actual);
  }
}
