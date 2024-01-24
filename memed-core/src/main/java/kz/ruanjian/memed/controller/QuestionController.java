package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.service.QuestionService;
import kz.ruanjian.memed.util.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

  private final QuestionService questionService;

  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/quizzes/{quizId}/questions/item")
  public Item<Question> findItem(@RequestHeader("x-visit-id") UUID visitId,
                                 @PathVariable Long quizId,
                                 @RequestParam Optional<Integer> number) {
    return questionService.findItem(visitId, quizId, number);
  }

  @PutMapping("/quizzes/{quizId}/questions/{id}")
  public Question provideAnswer(@RequestHeader("x-visit-id") UUID visitId,
                                @PathVariable Long quizId,
                                @PathVariable Long id,
                                @RequestBody @Valid AnswerDto answerDto) {
    return questionService.provideAnswer(visitId, quizId, id, answerDto);
  }
}
