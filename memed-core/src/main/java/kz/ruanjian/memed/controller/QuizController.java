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
  public Quiz findById(@RequestHeader("x-visit-id") UUID visitId,
                       @PathVariable Long id) {
    return quizService.findById(id);
  }

  @GetMapping("/private/quizzes")
  public Page<Quiz> findAll(@RequestHeader("x-visit-id") UUID visitId,
                            Pageable pageable) {
    return quizService.findAll(pageable, visitId);
  }

  @GetMapping("/templates/{templateId}/quizzes/request")
  public Quiz request(@PathVariable Long templateId,
                      @RequestHeader("x-visit-id") UUID visitId) {
    Visit visit = visitService.findById(visitId);
    return quizService.requestByTemplateIdAndVisit(templateId, visit);
  }

  @PutMapping("/quizzes/{id}/finalize")
  public Quiz finalize(@PathVariable Long id,
                       @RequestHeader("x-visit-id") UUID visitId) {
    Visit visit = visitService.findById(visitId);
    return quizService.finalizeByIdAndVisit(id, visit);
  }
}
