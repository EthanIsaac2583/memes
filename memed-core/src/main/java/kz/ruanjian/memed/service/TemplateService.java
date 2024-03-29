package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.mapper.TemplateMapper;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.respository.TemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TemplateService {

  private static final String TEMPLATE_NOT_FOUND = "Template not found";

  private final TemplateRepository templateRepository;
  private final TemplateMapper templateMapper;
  private final TaskService taskService;

  public TemplateService(TemplateRepository templateRepository,
                         TemplateMapper templateMapper,
                         TaskService taskService) {
    this.templateRepository = templateRepository;
    this.templateMapper = templateMapper;
    this.taskService = taskService;
  }

  public Template findById(Long id) {
    return templateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(TEMPLATE_NOT_FOUND));
  }

  public Page<Template> findAll(Pageable pageable) {
    return templateRepository.findAll(pageable);
  }

  @Transactional
  public Template save(TemplateDto templateDto) {
    Template template = mapToTemplate(templateDto);
    templateRepository.save(template);

    return template;
  }

  private Template mapToTemplate(TemplateDto templateDto) {
    Template template = templateMapper.toTemplate(templateDto);

    Set<Task> tasks = taskService.findAllById(templateDto.getTaskIds());
    template.setTasks(tasks);

    return template;
  }
}
