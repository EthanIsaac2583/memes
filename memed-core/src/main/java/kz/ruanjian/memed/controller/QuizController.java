package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class QuizController {

  private final QuizService quizService;

  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @GetMapping("/quizzes/{id}")
  public Quiz findById(@RequestHeader("x-visit-id") UUID visitId,
                       @PathVariable Long id) {
    return quizService.findByIdAndVisitId(id, visitId);
  }

  @GetMapping("/private/quizzes")
  public Page<Quiz> findAll(@RequestHeader("x-visit-id") UUID visitId,
                            Pageable pageable) {
    return quizService.findAllByVisitId(pageable, visitId);
  }

  @GetMapping("/templates/{templateId}/quizzes/request")
  public Quiz request(@PathVariable Long templateId,
                      @RequestHeader("x-visit-id") UUID visitId) {
    return quizService.requestByTemplateIdAndVisitId(templateId, visitId);
  }

  @PutMapping("/quizzes/{id}/finalize")
  public Quiz finalize(@PathVariable Long id,
                       @RequestHeader("x-visit-id") UUID visitId) {
    return quizService.finalizeByIdAndVisitId(id, visitId);
  }
}
