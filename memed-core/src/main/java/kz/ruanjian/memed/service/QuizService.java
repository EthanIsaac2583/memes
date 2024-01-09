package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.QuizRepository;
import kz.ruanjian.memed.respository.TemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.service.exception.DataConflictException;
import kz.ruanjian.memed.util.generator.QuizGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuizService {

  private final QuizGenerator quizGenerator;
  private final QuizRepository quizRepository;
  private final TemplateRepository templateRepository;

  public QuizService(QuizGenerator quizGenerator,
                     QuizRepository quizRepository,
                     TemplateRepository templateRepository) {
    this.quizGenerator = quizGenerator;
    this.quizRepository = quizRepository;
    this.templateRepository = templateRepository;
  }

  public Quiz findById(Long id) {
    return quizRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Quiz not found"));
  }

  public Page<Quiz> findAll(Pageable pageable) {
    return quizRepository.findAll(pageable);
  }

  @Transactional
  public Quiz requestByTemplateIdAndVisit(Long id, Visit visit) {
    return quizRepository
      .findTop1ByStatusAndTemplateIdAndVisit(QuizStatus.IN_PROGRESS, id, visit)
      .orElseGet(() -> generateByTemplateId(id, visit));
  }

  @Transactional
  public Quiz finalizeByIdAndVisit(Long id, Visit visit) {
    Quiz quiz = findById(id);
    verify(quiz, visit);

    quiz.setStatus(QuizStatus.DONE);
    quiz.setGrade(gradeQuiz(quiz));

    return quizRepository.save(quiz);
  }

  private Quiz generateByTemplateId(Long templateId, Visit visit) {
    Template template = findTemplateById(templateId);
    Quiz quiz = quizGenerator.generate(template);
    quiz.setVisit(visit);

    verifyQuizzesCountNotExceedLimitIfPresent(quiz);

    return quizRepository.save(quiz);
  }

  private Template findTemplateById(Long id) {
    return templateRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException("Template not found"));
  }

  private Long findQuizzesCountByTemplateAndVisit(Template template, Visit visit) {
    return quizRepository.countByTemplateAndVisit(template, visit);
  }

  private int gradeQuiz(Quiz quiz) {
    return questionGradesSum(quiz) / questionsCount(quiz);
  }

  private int questionGradesSum(Quiz quiz) {
    return quiz.getQuestions().stream()
      .mapToInt(Question::getGrade)
      .sum();
  }

  private int questionsCount(Quiz quiz) {
    return quiz.getQuestions().size();
  }

  private void verify(Quiz quiz, Visit visit) {
    verifyAllQuestionsAreAssessed(quiz);
    verifyQuizVisitAndCurrentVisitAreEqual(quiz, visit);
  }

  private void verifyAllQuestionsAreAssessed(Quiz quiz) {
    boolean hasUnAssessedQuestion = quiz.getQuestions().stream().anyMatch(question -> !question.isAssessed());

    if (hasUnAssessedQuestion) {
      throw new DataConflictException("Quiz has not assessed question");
    }
  }

  private void verifyQuizzesCountNotExceedLimitIfPresent(Quiz quiz) {
    if (quiz.getTemplate().getLimit() > 0) {
      Long quizzesCount = findQuizzesCountByTemplateAndVisit(quiz.getTemplate(), quiz.getVisit());
      if (quizzesCount >= quiz.getTemplate().getLimit()) {
        throw new DataConflictException("Reached max limit for quiz");
      }
    }
  }

  private void verifyQuizVisitAndCurrentVisitAreEqual(Quiz quiz, Visit visit) {
    if (!quiz.getVisit().equals(visit)) {
      throw new DataConflictException("Current visit is not as quiz creation visit");
    }
  }
}
