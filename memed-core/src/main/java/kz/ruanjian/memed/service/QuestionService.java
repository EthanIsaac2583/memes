package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.util.grader.GraderContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final GraderContext graderContext;

  public QuestionService(QuestionRepository questionRepository, GraderContext graderContext) {
    this.questionRepository = questionRepository;
    this.graderContext = graderContext;
  }

  public Question findNextQuestion(Long quizId) {
    return questionRepository
      .findTop1ByQuizIdAndAssessedIs(quizId, false)
      .orElseThrow(() -> new NotFoundException("Question not found"));
  }

  @Transactional
  public Question provideAnswer(AnswerDto answerDto) {
    Question question = findById(answerDto.getQuestionId());
    question.setAnswer(answerDto.getAnswer());
    question.setGrade(graderContext.grade(question));
    question.setAssessed(true);

    questionRepository.save(question);

    return question;
  }

  private Question findById(Long id) {
    return questionRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException("Question not found"));
  }
}
