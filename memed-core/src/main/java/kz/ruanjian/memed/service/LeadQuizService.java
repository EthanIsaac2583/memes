package kz.ruanjian.memed.service;

import kz.ruanjian.memed.generator.LeadQuizGenerator;
import kz.ruanjian.memed.model.LeadQuiz;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.respository.LeadQuizRepository;
import kz.ruanjian.memed.respository.QuizTemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeadQuizService {

  private static final String LEAD_QUIZ_NOT_FOUND_EXCEPTION = "Lead quiz not found";
  private static final String QUIZ_TEMPLATE_NOT_FOUND_EXCEPTION = "Quiz template not found";

  private final LeadQuizRepository leadQuizRepository;
  private final QuizTemplateRepository quizTemplateRepository;
  private final LeadQuizGenerator leadQuizGenerator;

  public LeadQuizService(LeadQuizRepository leadQuizRepository,
                         QuizTemplateRepository quizTemplateRepository,
                         LeadQuizGenerator leadQuizGenerator) {
    this.leadQuizRepository = leadQuizRepository;
    this.quizTemplateRepository = quizTemplateRepository;
    this.leadQuizGenerator = leadQuizGenerator;
  }

  public LeadQuiz findById(Long id) {
    return leadQuizRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(LEAD_QUIZ_NOT_FOUND_EXCEPTION));
  }

  @Transactional
  public LeadQuiz generateByQuizTemplateId(Long id) {
    Template template = findQuizTemplateById(id);
    LeadQuiz leadQuiz = leadQuizGenerator.generate(template);

    leadQuizRepository.save(leadQuiz);

    return leadQuiz;
  }

  private Template findQuizTemplateById(Long id) {
    return quizTemplateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(QUIZ_TEMPLATE_NOT_FOUND_EXCEPTION));
  }
}
