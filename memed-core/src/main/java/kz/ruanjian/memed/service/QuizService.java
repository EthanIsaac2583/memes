package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.QuizRepository;
import kz.ruanjian.memed.service.exception.ForbiddenException;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.service.exception.DataConflictException;
import kz.ruanjian.memed.util.generator.QuizGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class QuizService {

  private final QuizGenerator quizGenerator;
  private final QuizRepository quizRepository;
  private final TemplateService templateService;
  private final VisitService visitService;

  public QuizService(QuizGenerator quizGenerator,
                     QuizRepository quizRepository,
                     TemplateService templateService,
                     VisitService visitService) {
    this.quizGenerator = quizGenerator;
    this.quizRepository = quizRepository;
    this.templateService = templateService;
    this.visitService = visitService;
  }

  public Quiz findByIdAndVisitId(Long id, UUID visitId) {
    return quizRepository.findByIdAndVisitId(id, visitId)
      .orElseThrow(() -> new NotFoundException("Quiz not found"));
  }

  public Page<Quiz> findAllByVisitId(Pageable pageable, UUID visitId) {
    return quizRepository.findAll(quizRepository.visitIdEquals(visitId), pageable);
  }

  @Transactional
  public Quiz requestByTemplateIdAndVisitId(Long id, UUID visitId) {
    return quizRepository
      .findTop1ByStatusAndTemplateIdAndVisitId(QuizStatus.IN_PROGRESS, id, visitId)
      .orElseGet(() -> generateByTemplateIdAndVisitId(id, visitId));
  }

  @Transactional
  public Quiz finalizeByIdAndVisitId(Long id, UUID visitId) {
    Quiz quiz = findByIdAndVisitId(id, visitId);
    verifyAllQuestionsAreAssessed(quiz);

    quiz.setStatus(QuizStatus.DONE);
    quiz.setGrade(gradeQuiz(quiz));

    return quizRepository.save(quiz);
  }

  private Quiz generateByTemplateIdAndVisitId(Long templateId, UUID visitId) {
    Template template = templateService.findById(templateId);
    Visit visit = visitService.findById(visitId);
    Quiz quiz = quizGenerator.generate(template, visit);

    verifyLimitIfPresent(quiz);

    return quizRepository.save(quiz);
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

  private void verifyAllQuestionsAreAssessed(Quiz quiz) {
    boolean hasUnAssessedQuestion = quiz.getQuestions().stream().anyMatch(question -> !question.isAssessed());

    if (hasUnAssessedQuestion) {
      throw new DataConflictException("Quiz has not assessed question");
    }
  }

  private void verifyLimitIfPresent(Quiz quiz) {
    if (quiz.getTemplate().getLimit() > 0) {
      Long quizzesCount = findQuizzesCountByTemplateAndVisit(quiz.getTemplate(), quiz.getVisit());
      if (quizzesCount >= quiz.getTemplate().getLimit()) {
        throw new ForbiddenException("Reached limit for quiz");
      }
    }
  }
}
