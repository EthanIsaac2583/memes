package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.singularrepository.Single;
import kz.ruanjian.memed.service.QuestionService;
import kz.ruanjian.memed.util.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/quizzes/{quizId}/questions/next")
  public Question findNextQuestionByQuizId(@PathVariable Long quizId) {
    return questionService.findNextQuestion(quizId);
  }

  @GetMapping("/questions/item")
  public Item<Question> findItem(@RequestParam Long quizId, @RequestParam Integer number) {
    return questionService.findItem(quizId, number);
  }

  @GetMapping("/questions/single")
  public Single<Question> findSingle(@RequestParam Long quizId,
                                     @RequestParam Integer number) {
    return questionService.findSingle(quizId, number);
  }

  @PatchMapping("/questions/{id}")
  public Question provideAnswer(@PathVariable Long id, @RequestBody @Valid AnswerDto answer) {
    answer.setQuestionId(id);

    return questionService.provideAnswer(answer);
  }
}
