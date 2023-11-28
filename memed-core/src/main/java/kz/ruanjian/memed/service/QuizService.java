package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.respository.QuizRepository;
import kz.ruanjian.memed.respository.TemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import kz.ruanjian.memed.util.generator.QuizGenerator;
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

  @Transactional
  public Quiz findOrCreateByTemplateId(Long id) {
    return quizRepository
      .findByTemplateId(id)
      .orElse(generateQuizByTemplateId(id));
  }

  private Quiz generateQuizByTemplateId(Long id) {
    Template template = findTemplateById(id);
    Quiz quiz = quizGenerator.generate(template);
    quizRepository.save(quiz);

    return quiz;
  }

  private Template findTemplateById(Long id) {
    return templateRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException("Template not found"));
  }
}
