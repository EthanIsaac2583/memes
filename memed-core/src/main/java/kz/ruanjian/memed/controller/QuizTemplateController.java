package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.QuizTemplateDto;
import kz.ruanjian.memed.model.QuizTemplate;
import kz.ruanjian.memed.service.QuizTemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quiz-templates")
//@CrossOrigin
public class QuizTemplateController {

  private final QuizTemplateService quizTemplateService;

  public QuizTemplateController(QuizTemplateService quizTemplateService) {
    this.quizTemplateService = quizTemplateService;
  }

  @GetMapping("/{id}")
  public QuizTemplate findById(@PathVariable Long id) {
    return quizTemplateService.findById(id);
  }

  @GetMapping
  public Page<QuizTemplate> findAll(Pageable pageable) {
    return quizTemplateService.findAll(pageable);
  }

  @PostMapping
  public void create(@RequestBody @Valid QuizTemplateDto quizTemplate) {
    quizTemplateService.save(quizTemplate);
  }
}
