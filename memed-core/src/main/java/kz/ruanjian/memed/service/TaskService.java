package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.TaskDto;
import kz.ruanjian.memed.mapper.TaskMapper;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.TaskRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskService {

  private static final String NOT_FOUND_EXCEPTION = "Task not found";

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public Task findById(Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION));
  }

  public Set<Task> findAllById(Set<Long> ids) {
    return new HashSet<>(taskRepository.findAllById(ids));
  }

  public Page<Task> findAll(Pageable pageable) {
    return taskRepository.findAll(pageable);
  }

  @Transactional
  public void save(TaskDto taskDto) {
    Task task = mapToTask(taskDto);

    taskRepository.save(task);
  }

  @Transactional
  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

  private Task mapToTask(TaskDto taskDto) {
    return taskMapper.toTask(taskDto);
  }
}
