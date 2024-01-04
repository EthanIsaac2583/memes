package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Template;
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

  private final QuizRepository quizRepository;
  private final TemplateRepository templateRepository;
  private final QuizGenerator quizGenerator;

  public QuizService(QuizRepository quizRepository,
                     TemplateRepository templateRepository,
                     QuizGenerator quizGenerator) {
    this.quizRepository = quizRepository;
    this.templateRepository = templateRepository;
    this.quizGenerator = quizGenerator;
  }

  public Quiz findById(Long id) {
    return quizRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Quiz not found"));
  }

  public Page<Quiz> findAll(Pageable pageable) {
    return quizRepository.findAll(pageable);
  }

  @Transactional
  public Quiz requestByTemplateId(Long id) {
    return quizRepository
      .findTop1ByStatusAndTemplateId(QuizStatus.IN_PROGRESS, id)
      .orElseGet(() -> generateByTemplateId(id));
  }

  @Transactional
  public Quiz finalizeById(Long id) {
    Quiz quiz = findById(id);
    verify(quiz);

    quiz.setStatus(QuizStatus.DONE);
    quiz.setGrade(gradeQuiz(quiz));

    quizRepository.save(quiz);

    return quiz;
  }

  private Quiz generateByTemplateId(Long templateId) {
    Template template = findTemplateById(templateId);
    Quiz quiz = quizGenerator.generate(template);

    verifyQuizzesCountUnderLimitIfLimitPresent(quiz);

    quizRepository.save(quiz);
    return quiz;
  }

  private Template findTemplateById(Long id) {
    return templateRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException("Template not found"));
  }

  private Long findQuizzesCountByTemplateId(Long id) {
    return quizRepository.count(quizRepository.templateIdEquals(id));
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

  private void verify(Quiz quiz) {
    verifyAllQuestionsAreAssessed(quiz);
  }

  private void verifyAllQuestionsAreAssessed(Quiz quiz) {
    boolean hasUnAssessedQuestion = quiz.getQuestions().stream().anyMatch(question -> !question.isAssessed());

    if (hasUnAssessedQuestion) {
      throw new DataConflictException("Quiz has not assessed question");
    }
  }

  private void verifyQuizzesCountUnderLimitIfLimitPresent(Quiz quiz) {
    if (quiz.getTemplate().getLimit() > 0) {
      Long quizzesCount = findQuizzesCountByTemplateId(quiz.getTemplate().getId());
      if (quizzesCount >= quiz.getTemplate().getLimit()) {
        throw new DataConflictException("Reached limit of quizzes for all users. Fix that with auth.");
      }
    }
  }
}
