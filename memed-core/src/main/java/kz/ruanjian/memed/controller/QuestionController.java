package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.NextQuestionDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

  private final QuestionService questionService;

  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/quizzes/questions/next")
  public Question findNextQuestionByQuizId(@Valid NextQuestionDto nextQuestion) {
    return questionService.findNextQuestion(nextQuestion);
  }
}
