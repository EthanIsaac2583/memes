package kz.ruanjian.memed.util.generator;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizGeneratorTest {

  QuizGenerator quizGenerator;
  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    quizGenerator = new QuizGenerator();
    dataGenerator = new DataGenerator();
  }

  @Test
  void generate_should_when1() {
    Visit visit = dataGenerator.generateVisit();
  }
}
