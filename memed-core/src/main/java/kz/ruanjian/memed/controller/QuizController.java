package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

  private final QuizService quizService;

  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  // todo post method
  @GetMapping("/request")
  public Quiz request(@RequestParam Long templateId) {
    return quizService.request(templateId);
  }

  @PatchMapping("/{id}/finalize")
  public Quiz finalizeById(@PathVariable Long id) {
    return quizService.finalizeById(id);
  }
}
