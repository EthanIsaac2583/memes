package kz.ruanjian.memed.generator;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.LeadQuizAnswer;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Task;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LeadQuizGenerator {

  public Quiz generate(Template template) {
    Quiz quiz = new Quiz();

    quiz.setTemplate(template);
    quiz.setAnswers(generateAnswers(template.getTasks(), quiz));

    return quiz;
  }

  private Set<LeadQuizAnswer> generateAnswers(Set<Task> tasks, Quiz quiz) {
    return tasks.stream()
      .map(task -> generateAnswer(task, quiz))
      .collect(Collectors.toSet());
  }

  private LeadQuizAnswer generateAnswer(Task task, Quiz quiz) {
    LeadQuizAnswer answer = new LeadQuizAnswer();

    answer.setQuiz(quiz);
    answer.setTask(task);

    return answer;
  }
}
