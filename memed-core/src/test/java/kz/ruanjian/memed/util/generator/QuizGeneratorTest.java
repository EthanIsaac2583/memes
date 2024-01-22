package kz.ruanjian.memed.util.generator;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    Template template = dataGenerator.generateTemplate();

    Quiz expected = generateQuiz(template, visit);

    Quiz actual = quizGenerator.generate(template, visit);

    assertEquals(expected, actual);
  }

  private Quiz generateQuiz(Template template, Visit visit) {
    return Quiz.builder()
      .visit(visit)
      .template(template)
      .questions(generateQuestions(template, visit))
      .status(QuizStatus.IN_PROGRESS)
      .grade(0)
      .build();
  }

  private Set<Question> generateQuestions(Template template, Visit visit) {
    return template.getTasks().stream()
      .map(task -> this.generateQuestion(task, visit))
      .collect(Collectors.toSet());
  }

  private Question generateQuestion(Task task, Visit visit) {
    return Question.builder()
      .task(task)
      .assessed(false)
      .grade(0)
      .visit(visit)
      .build();
  }
}
