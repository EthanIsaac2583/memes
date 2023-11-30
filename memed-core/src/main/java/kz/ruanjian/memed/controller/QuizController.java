package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.QuizRequestDto;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

  private final QuizService quizService;

  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @PostMapping("/request")
  public Quiz request(@RequestBody @Valid QuizRequestDto quizRequest) {
    return quizService.request(quizRequest);
  }
}
