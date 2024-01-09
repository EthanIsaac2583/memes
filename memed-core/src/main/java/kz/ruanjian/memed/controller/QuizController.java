package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.service.QuizService;
import kz.ruanjian.memed.service.VisitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class QuizController {

  private final QuizService quizService;
  private final VisitService visitService;

  public QuizController(QuizService quizService,
                        VisitService visitService) {
    this.quizService = quizService;
    this.visitService = visitService;
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
  public Quiz requestByTemplateId(@PathVariable Long templateId,
                                  @RequestHeader("x-visit-id") UUID visitId,
                                  @RequestHeader("x-user_id") Optional<Long> userId) {
    Visit visit = visitService.findById(visitId);
    return quizService.requestByTemplateId(templateId, visit);
  }

  @PutMapping("/quizzes/{id}/finalize")
  public Quiz finalizeById(@PathVariable Long id) {
    return quizService.finalizeById(id);
  }
}
