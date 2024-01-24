package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.TaskDto;
import kz.ruanjian.memed.mapper.TaskMapper;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public Set<Task> findAllById(Set<Long> ids) {
    return new HashSet<>(taskRepository.findAllById(ids));
  }

  @Transactional
  public void save(TaskDto taskDto) {
    Task task = taskMapper.toTask(taskDto);

    taskRepository.save(task);
  }
}
