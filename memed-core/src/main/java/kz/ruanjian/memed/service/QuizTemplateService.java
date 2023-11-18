package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.QuizTemplateDto;
import kz.ruanjian.memed.model.QuizTemplate;
import kz.ruanjian.memed.respository.QuizTemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuizTemplateService {

  private final String NOT_FOUND_EXCEPTION = "Quiz template not found";

  private final QuizTemplateRepository quizTemplateRepository;

  public QuizTemplateService(QuizTemplateRepository quizTemplateRepository) {
    this.quizTemplateRepository = quizTemplateRepository;
  }

  public QuizTemplate findById(Long id) {
    return quizTemplateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION));
  }

  public QuizTemplate save(QuizTemplateDto quizTemplateDto) {
  }
}
