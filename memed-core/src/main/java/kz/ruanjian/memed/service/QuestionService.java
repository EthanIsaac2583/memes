package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AnswerDto;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.QuestionMetaViewRepository;
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

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final QuestionMetaViewRepository questionMetaViewRepository;
  private final GraderContext graderContext;

  public QuestionService(QuestionRepository questionRepository,
                         QuestionMetaViewRepository questionMetaViewRepository,
                         GraderContext graderContext) {
    this.questionRepository = questionRepository;
    this.questionMetaViewRepository = questionMetaViewRepository;
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
    System.out.println("-----_> " + questionMetaViewRepository.findTop1ByAssessedIs(false));

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

  private Pageable toPageable(Itemized itemized) {
    int number = Math.max(itemized.getNumber() - 1, 0);
    int size = 1;
    Sort sort = Sort.by(Sort.Order.asc("id"));

    return PageRequest.of(number, size, sort);
  }

  private Specification<Question> toSpecification(Itemized itemized) {
    return questionRepository.quizIdEquals(itemized.getQuizId());
  }

  private Item<Question> toItem(Page<Question> questionPage) {
    Item<Question> item = new Item<>();

    item.setTotalItems(questionPage.getTotalPages());
    item.setNumber(questionPage.getNumber() + 1);
    item.setHasPrevious(questionPage.hasPrevious());
    item.setHasPrevious(questionPage.hasPrevious());

    if (!questionPage.isEmpty()) {
      item.setItem(questionPage.getContent().get(0));
    }

    return item;
  }
}
