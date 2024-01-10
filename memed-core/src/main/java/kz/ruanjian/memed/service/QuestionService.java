package kz.ruanjian.memed.service;

import kz.ruanjian.memed.mapper.ItemMapper;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.QuestionRepository;
import kz.ruanjian.memed.service.exception.DataConflictException;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.util.Item;
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

  private static final String QUESTION_NOT_FOUND = "Question not found";

  private final QuestionRepository questionRepository;
  private final GraderContext graderContext;
  private final ItemMapper itemMapper;

  public QuestionService(QuestionRepository questionRepository,
                         GraderContext graderContext,
                         ItemMapper itemMapper) {
    this.questionRepository = questionRepository;
    this.graderContext = graderContext;
    this.itemMapper = itemMapper;
  }

  public Question findByIdAndQuizIdAndVisit(Long id, Long quizId, Visit visit) {
    return questionRepository
      .findByIdAndQuizIdAndVisit(id, quizId, visit)
      .orElseThrow(() -> new NotFoundException(QUESTION_NOT_FOUND));
  }

  public Item<Question> findQuestionsItem(Visit visit, Long quizId, Optional<Integer> number) {
    int questionNumber = identifyAssessableQuestionNumber(quizId, number);
    Pageable pageable = generateSingleQuestionPageable(questionNumber);
    Specification<Question> specification = questionRepository.quizIdEquals(quizId);
    Page<Question> questionPage = questionRepository.findAll(specification, pageable);

    if (questionPage.isEmpty()) {
      throw new NotFoundException(QUESTION_NOT_FOUND);
    }

    return itemMapper.toItem(questionPage);
  }

  public Page<Question> findAll(Pageable pageable) {
    return questionRepository.findAll(pageable);
  }

  @Transactional
  public Question provideAnswer(Visit visit, Question question) {
    question.setGrade(graderContext.grade(question));
    question.setAssessed(true);

    verify(visit, question);

    return questionRepository.save(question);
  }

  private int identifyAssessableQuestionNumber(Long quizId, Optional<Integer> number) {
    if (number.isPresent()) {
      return Math.max(number.get() - 1, 0);
    }

    int firstAssessableNumber = findFirstAssessableQuestionNumberByQuizId(quizId);
    return Math.max(firstAssessableNumber - 1, 0);
  }

  private int findFirstAssessableQuestionNumberByQuizId(Long id) {
    return questionRepository
      .findFirstAssessableQuestionNumber(id)
      .map(Long::intValue)
      .orElseThrow(() -> new NotFoundException(QUESTION_NOT_FOUND));
  }

  private void verify(Visit visit, Question question) {
    verifySameVisit(visit, question);
  }

  private void verifySameVisit(Visit visit, Question question) {
    if (!question.getQuiz().getVisit().equals(visit)) {
      throw new DataConflictException("Different visit");
    }
  }

  private Pageable generateSingleQuestionPageable(int number) {
    int size = 1;
    Sort sort = Sort.by(Sort.Order.asc("number"));

    return PageRequest.of(number, size, sort);
  }
}
