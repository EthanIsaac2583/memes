package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.QuizTemplateRepository;
import kz.ruanjian.memed.respository.TaskRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuizTemplateService {

  private static final String NOT_FOUND_EXCEPTION = "Quiz template not found";

  private final QuizTemplateRepository quizTemplateRepository;
  private final TaskRepository taskRepository;
  private final QuizTemplateMapper quizTemplateMapper;

  public QuizTemplateService(QuizTemplateRepository quizTemplateRepository,
                             TaskRepository taskRepository,
                             QuizTemplateMapper quizTemplateMapper) {
    this.quizTemplateRepository = quizTemplateRepository;
    this.taskRepository = taskRepository;
    this.quizTemplateMapper = quizTemplateMapper;
  }

  public Template findById(Long id) {
    return quizTemplateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION));
  }

  public Page<Template> findAll(Pageable pageable) {
    return quizTemplateRepository.findAll(pageable);
  }

  @Transactional
  public Template save(QuizTemplateDto quizTemplateDto) {
    Template template = mapToQuizTemplate(quizTemplateDto);

    quizTemplateRepository.save(template);

    return template;
  }

  private Template mapToQuizTemplate(QuizTemplateDto quizTemplateDto) {
    Template template = quizTemplateMapper.toQuizTemplate(quizTemplateDto);
    template.setTasks(findTasksById(quizTemplateDto.getTaskIds()));

    return template;
  }

  private Set<Task> findTasksById(Set<Long> ids) {
    return new HashSet<>(taskRepository.findAllById(ids));
  }
}
