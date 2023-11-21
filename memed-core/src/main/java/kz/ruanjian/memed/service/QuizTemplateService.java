package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.QuizTemplateDto;
import kz.ruanjian.memed.mapper.QuizTemplateMapper;
import kz.ruanjian.memed.model.QuizTemplate;
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

  public QuizTemplate findById(Long id) {
    return quizTemplateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION));
  }

  public Page<QuizTemplate> findAll(Pageable pageable) {
    return quizTemplateRepository.findAll(pageable);
  }

  @Transactional
  public QuizTemplate save(QuizTemplateDto quizTemplateDto) {
    QuizTemplate quizTemplate = mapToQuizTemplate(quizTemplateDto);

    quizTemplateRepository.save(quizTemplate);

    return quizTemplate;
  }

  private QuizTemplate mapToQuizTemplate(QuizTemplateDto quizTemplateDto) {
    QuizTemplate quizTemplate = quizTemplateMapper.toQuizTemplate(quizTemplateDto);
    quizTemplate.setTasks(findTasksById(quizTemplateDto.getTaskIds()));

    return quizTemplate;
  }

  private Set<Task> findTasksById(Set<Long> ids) {
    return new HashSet<>(taskRepository.findAllById(ids));
  }
}
