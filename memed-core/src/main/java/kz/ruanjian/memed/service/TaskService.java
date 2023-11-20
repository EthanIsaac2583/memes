package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.TaskDto;
import kz.ruanjian.memed.mapper.TaskMapper;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.TaskRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public Task findById(Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Task not found"));
  }

  @Transactional
  public void save(TaskDto taskDto) {
    Task task = mapToTask(taskDto);

    taskRepository.save(task);
  }

  private Task mapToTask(TaskDto taskDto) {
    return taskMapper.toTask(taskDto);
  }
}
