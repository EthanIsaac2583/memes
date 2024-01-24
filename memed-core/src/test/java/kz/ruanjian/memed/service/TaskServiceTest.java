package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.TaskDto;
import kz.ruanjian.memed.mapper.TaskMapper;
import kz.ruanjian.memed.mapper.TaskMapperImpl;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

  @Mock
  TaskRepository taskRepository;

  @Spy
  TaskMapper taskMapper = new TaskMapperImpl();

  @InjectMocks
  TaskService taskService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findAllById_shouldReturnTasks_whenIdsPassed() {
    List<Task> tasks = dataGenerator.generateTasks(10);
    Set<Long> ids = getTaskIds(tasks);
    doReturn(tasks).when(taskRepository).findAllById(ids);

    Set<Task> expected = new HashSet<>(tasks);

    Set<Task> actual = taskService.findAllById(ids);

    assertEquals(expected, actual);
    verify(taskRepository).findAllById(ids);
  }

  @Test
  void save_shouldSaveTask_whenValidTaskDtoPassed() {
    Task expected = dataGenerator.generateTask();
    TaskDto taskDto = generateTaskDto(expected);
    doReturn(expected).when(taskRepository).save(expected);

    Task actual = taskService.save(taskDto);

    assertEquals(expected, actual);
    verify(taskMapper).toTask(taskDto);
    verify(taskRepository).save(expected);
  }

  private Set<Long> getTaskIds(List<Task> tasks) {
    return tasks.stream()
      .map(Task::getId)
      .collect(Collectors.toSet());
  }

  private TaskDto generateTaskDto(Task task) {
    TaskDto taskDto = new TaskDto();

    taskDto.setId(task.getId());
    taskDto.setName(task.getName());
    taskDto.setBody(task.getBody());
    taskDto.setBlank(task.getBlank());
    taskDto.setAnswer(task.getAnswer());

    return taskDto;
  }
}
