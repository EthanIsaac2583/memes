package kz.ruanjian.memed.controller;

import jakarta.validation.Valid;
import kz.ruanjian.memed.dto.TaskDto;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/{id}")
  public Task findById(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @GetMapping
  public Page<Task> findAll(Pageable pageable) {
    return taskService.findAll(pageable);
  }

  @PostMapping
  public void create(@RequestBody @Valid TaskDto task) {
    taskService.save(task);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id,
                     @RequestBody @Valid TaskDto task) {
    taskService.findById(id);
    task.setId(id);

    taskService.save(task);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    taskService.deleteById(id);
  }
}
