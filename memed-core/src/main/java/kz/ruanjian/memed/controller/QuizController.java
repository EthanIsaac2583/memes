package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class QuizController {

  private final QuizService quizService;

  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @GetMapping("/templates/{templateId}/quizzes")
  public Quiz findOrCreateQuizByTemplateId(@PathVariable Long templateId) {
    return quizService.findOrCreateByTemplateId(templateId);
  }
}
