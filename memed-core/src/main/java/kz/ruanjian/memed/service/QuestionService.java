package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.util.Item;
import kz.ruanjian.memed.util.Itemized;
import kz.ruanjian.memed.util.grader.GraderContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  public Page<Question> findAll(Pageable pageable) {
    return questionRepository.findAll(pageable);
  }

  public Item<Question> findItem(Itemized itemized) {
    Pageable pageable = toPageable(itemized);
    Page<Question> questionPage = questionRepository.findAll(pageable);

    return toItem(questionPage);
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

  private Pageable toPageable(Itemized itemized) {
    return PageRequest.of(itemized.getNumber(), 1, Sort.by(Sort.Order.asc("id")));
  }

  private Item<Question> toItem(Page<Question> questionPage) {
    Item<Question> item = new Item<>();

    item.setTotalItems(questionPage.getTotalPages());
    item.setNumber(questionPage.getNumber());
    item.setHasPrevious(questionPage.hasPrevious());
    item.setHasPrevious(questionPage.hasPrevious());

    if (!questionPage.isEmpty()) {
      item.setContent(questionPage.getContent().get(0));
    }

    return item;
  }
}
