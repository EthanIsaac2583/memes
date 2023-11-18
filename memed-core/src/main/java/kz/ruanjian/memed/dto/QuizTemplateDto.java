package kz.ruanjian.memed.dto;

import java.util.Set;

public class QuizTemplateDto {

  private Long id;
  private String name;
  private Set<Long> taskIds;

  public QuizTemplateDto() {
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

  public Set<Long> getTaskIds() {
    return taskIds;
  }

  public void setTaskIds(Set<Long> taskIds) {
    this.taskIds = taskIds;
  }
}
