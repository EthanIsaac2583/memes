package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.TaskDto;
import kz.ruanjian.memed.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

  Task toTask(TaskDto taskDto);
}
