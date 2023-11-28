package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.respository.TemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemplateService {

  private static final String TEMPLATE_NOT_FOUND = "Template not found";

  private final TemplateRepository templateRepository;

  public TemplateService(TemplateRepository templateRepository) {
    this.templateRepository = templateRepository;
  }

  public Template findById(Long id) {
    return templateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(TEMPLATE_NOT_FOUND));
  }

  @Transactional
  public Template save(TemplateDto templateDto) {
    return new Template();
  }

  @Transactional
  public void deleteById(Long id) {
    templateRepository.deleteById(id);
  }
}
