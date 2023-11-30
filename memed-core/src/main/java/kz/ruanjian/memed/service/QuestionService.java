package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.NextQuestionDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;

  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public Question findNextQuestion(NextQuestionDto nextQuestionDto) {
    return questionRepository
      .findOne(nextQuestionDto)
      .orElseThrow(() -> new NotFoundException("Question not found"));
  }
}
