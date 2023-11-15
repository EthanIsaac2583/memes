package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.TaskRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task findById(Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Task not found"));
  }

  public void save() {
  }
}
