package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

  private final QuestionService questionService;

  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/quizzes/questions/next")
  public Question findNextQuestionByQuizId(@RequestParam Long quizId) {
    System.out.println("-------> quizId " + quizId);
    return new Question();
  }
}
