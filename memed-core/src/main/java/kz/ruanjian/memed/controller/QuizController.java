package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.service.QuizService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class QuizController {

  private final QuizService quizService;

  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  // should be GET method (idempotent)
  @PostMapping("/templates/{templateId}/quizzes/request")
  public Quiz requestByTemplateId(@PathVariable Long templateId) {
    return quizService.requestByTemplateId(templateId);
  }

  @PatchMapping("/quizzes/{id}/finalize")
  public Quiz finalizeById(@PathVariable Long id) {
    return quizService.finalizeById(id);
  }
}
