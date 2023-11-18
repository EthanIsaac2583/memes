package kz.ruanjian.memed.generator;

import kz.ruanjian.memed.model.LeadQuiz;
import kz.ruanjian.memed.model.QuizTemplate;
import org.springframework.stereotype.Component;

@Component
public class LeadQuizGenerator {

  public LeadQuiz generate(QuizTemplate quizTemplate) {
    return new LeadQuiz();
  }
}
