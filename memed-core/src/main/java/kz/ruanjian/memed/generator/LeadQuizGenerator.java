package kz.ruanjian.memed.generator;

import kz.ruanjian.memed.model.LeadQuiz;
import kz.ruanjian.memed.model.LeadQuizAnswer;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Task;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LeadQuizGenerator {

  public LeadQuiz generate(Template template) {
    LeadQuiz leadQuiz = new LeadQuiz();

    leadQuiz.setTemplate(template);
    leadQuiz.setAnswers(generateAnswers(template.getTasks(), leadQuiz));

    return leadQuiz;
  }

  private Set<LeadQuizAnswer> generateAnswers(Set<Task> tasks, LeadQuiz leadQuiz) {
    return tasks.stream()
      .map(task -> generateAnswer(task, leadQuiz))
      .collect(Collectors.toSet());
  }

  private LeadQuizAnswer generateAnswer(Task task, LeadQuiz leadQuiz) {
    LeadQuizAnswer answer = new LeadQuizAnswer();

    answer.setQuiz(leadQuiz);
    answer.setTask(task);

    return answer;
  }
}
