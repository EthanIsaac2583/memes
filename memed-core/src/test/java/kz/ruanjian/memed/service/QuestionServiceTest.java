package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.mapper.ItemMapper;
import kz.ruanjian.memed.mapper.ItemMapperImpl;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.util.grader.GraderContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

  @Mock
  QuestionRepository questionRepository;

  @Mock
  GraderContext graderContext;

  @Spy
  ItemMapper itemMapper = new ItemMapperImpl();

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findItem() {
  }

  @Test
  void provideAnswer_should_when1() {
    Question question = dataGenerator.generateQuestion();
  }

  @Test
  void provideAnswer_should_when2() {
  }
}
