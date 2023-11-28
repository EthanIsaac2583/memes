package kz.ruanjian.memed.util.generator;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.model.Template;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuizGenerator {

  public Quiz generate(Template template) {
    Quiz quiz = new Quiz();
    quiz.setStatus(QuizStatus.IN_PROGRESS);

    quiz.setTemplate(template);
    quiz.setQuestions(generateQuestions(template, quiz));

    return quiz;
  }

  private Set<Question> generateQuestions(Template template, Quiz quiz) {
    return template.getTasks().stream()
      .map(task -> generateQuestion(task, quiz))
      .collect(Collectors.toSet());
  }

  private Question generateQuestion(Task task, Quiz quiz) {
    Question question = new Question();

    question.setQuiz(quiz);
    question.setTask(task);

    return question;
  }
}
