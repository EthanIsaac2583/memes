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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    Specification<Question> specification = toSpecification(itemized);
    Page<Question> questionPage = questionRepository.findAll(specification, pageable);

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

  private Optional<Long> findFirstAssessableQuestion(Itemized itemized) {
    return questionRepository
      .findFirstAssessableQuestionNumber(itemized.getQuizId());
  }

  private Pageable toPageable(Itemized itemized) {
    int number = determineNumber(itemized);
    int size = 1;
    Sort sort = Sort.by(Sort.Order.asc("number"));

    return PageRequest.of(number, size, sort);
  }

  private int determineNumber(Itemized itemized) {
    if (itemized.getNumber() == null) {
      return findFirstAssessableQuestion(itemized)
        .map(Math::toIntExact)
        .map(number -> Math.max(number - 1, 0))
        .orElseThrow(() -> new NotFoundException("Question not found"));
    }

    return Math.max(itemized.getNumber() - 1, 0);
  }

  private Specification<Question> toSpecification(Itemized itemized) {
    return questionRepository.quizIdEquals(itemized.getQuizId());
  }

  private Item<Question> toItem(Page<Question> questionPage) {
    Item<Question> item = new Item<>();

    item.setTotalItems(questionPage.getTotalPages());
    item.setNumber(questionPage.getNumber() + 1);
    item.setHasPrevious(questionPage.hasPrevious());
    item.setHasNext(questionPage.hasNext());
    item.setLast(questionPage.isLast());

    if (!questionPage.isEmpty()) {
      item.setContent(questionPage.getContent().get(0));
    }

    return item;
  }
}
