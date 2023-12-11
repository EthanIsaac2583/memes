package kz.ruanjian.memed.util.generator;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.model.Template;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class QuizGenerator {

  private static final Integer NUMBER_START = 1;

  public Quiz generate(Template template) {
    Quiz quiz = new Quiz();
    quiz.setStatus(QuizStatus.IN_PROGRESS);

    quiz.setTemplate(template);
    quiz.setQuestions(generateQuestions(template, quiz));

    return quiz;
  }

  private Set<Question> generateQuestions(Template template, Quiz quiz) {
    AtomicInteger numberContext = createNumberContext();
    Set<Task> tasks = getShuffledTasks(template);

    return tasks.stream()
      .map(task -> generateQuestion(task, quiz, numberContext.getAndIncrement()))
      .collect(Collectors.toSet());
  }

  private Question generateQuestion(Task task, Quiz quiz, Integer number) {
    Question question = new Question();

    question.setQuiz(quiz);
    question.setTask(task);
    question.setNumber(number);

    return question;
  }

  private Set<Task> getShuffledTasks(Template template) {
    ArrayList<Task> tasks = new ArrayList<>(template.getTasks());
    Collections.shuffle(tasks);

    return new HashSet<>(tasks);
  }

  private AtomicInteger createNumberContext() {
    return new AtomicInteger(NUMBER_START);
  }
}
