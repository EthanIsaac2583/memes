package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/quizzes/{id}")
  public Quiz findById(@PathVariable Long id) {
    return quizService.findById(id);
  }

  @GetMapping("/quizzes")
  public Page<Quiz> findAll(Pageable pageable) {
    return quizService.findAll(pageable);
  }

  @GetMapping("/templates/{templateId}/quizzes/request")
  public Quiz requestByTemplateId(@PathVariable Long templateId) {
    return quizService.requestByTemplateId(templateId);
  }

  @PatchMapping("/quizzes/{id}/finalize")
  public Quiz finalizeById(@PathVariable Long id) {
    return quizService.finalizeById(id);
  }
}
