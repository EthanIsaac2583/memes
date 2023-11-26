package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.LeadQuizAnswerGradeDto;
import kz.ruanjian.memed.dto.LeadQuizAnswerProvideDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.service.LeadQuizAnswerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lead-quiz-answers")
public class LeadQuizAnswerController {

  private final LeadQuizAnswerService leadQuizAnswerService;

  public LeadQuizAnswerController(LeadQuizAnswerService leadQuizAnswerService) {
    this.leadQuizAnswerService = leadQuizAnswerService;
  }

  @GetMapping("/{id}")
  public Question findById(@PathVariable Long id) {
    return leadQuizAnswerService.findById(id);
  }

  @PutMapping("/{id}/provide")
  public void provide(@PathVariable Long id,
                      @RequestBody @Valid LeadQuizAnswerProvideDto leadQuizAnswer) {
    leadQuizAnswer.setId(id);
    leadQuizAnswerService.provide(leadQuizAnswer);
  }

  @PutMapping("/{id}/grade")
  public void grade(@PathVariable Long id,
                    @RequestBody @Valid LeadQuizAnswerGradeDto leadQuizAnswer) {
    leadQuizAnswer.setId(id);
    leadQuizAnswerService.grade(leadQuizAnswer);
  }
}
