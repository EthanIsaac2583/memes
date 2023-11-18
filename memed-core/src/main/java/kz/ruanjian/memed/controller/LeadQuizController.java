package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.LeadQuiz;
import kz.ruanjian.memed.service.LeadQuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LeadQuizController {

  private final LeadQuizService leadQuizService;

  public LeadQuizController(LeadQuizService leadQuizService) {
    this.leadQuizService = leadQuizService;
  }

  @GetMapping("/lead-quizzes/{id}")
  public LeadQuiz findById(@PathVariable Long id) {
    return leadQuizService.findById(id);
  }

  @PostMapping("/quiz-templates/{quizTemplateId}/lead-quizzes")
  public void generateByQuizTemplateId(@PathVariable Long quizTemplateId) {
    leadQuizService.generateByQuizTemplateId(quizTemplateId);
  }
}
