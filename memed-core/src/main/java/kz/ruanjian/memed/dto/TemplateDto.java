package kz.ruanjian.memed.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.Set;

public class TemplateDto {

  private Long id;

  @NotEmpty
  private String name;

  private String description;

  @NotEmpty
  private Set<Long> taskIds;

  public TemplateDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Long> getTaskIds() {
    return taskIds;
  }

  public void setTaskIds(Set<Long> taskIds) {
    this.taskIds = taskIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    TemplateDto that = (TemplateDto) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(taskIds, that.taskIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, taskIds);
  }

  @Override
  public String toString() {
    return "TemplateDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", taskIds=" + taskIds +
      '}';
  }
}
