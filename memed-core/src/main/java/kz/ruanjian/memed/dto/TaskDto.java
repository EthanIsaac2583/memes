package kz.ruanjian.memed.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.deserializer.AnswerDeserializer;
import kz.ruanjian.memed.pojo.deserializer.BlankDeserializer;
import kz.ruanjian.memed.pojo.deserializer.QuestionDeserializer;
import kz.ruanjian.memed.pojo.body.Body;

import java.util.Objects;

public class TaskDto {

  private Long id;

  @NotEmpty
  private String name;

  private String description;

  @Valid
  @NotNull
  @JsonDeserialize(using = QuestionDeserializer.class)
  private Body body;

  @Valid
  @NotNull
  @JsonDeserialize(using = BlankDeserializer.class)
  private Blank blank;

  @Valid
  @NotNull
  @JsonDeserialize(using = AnswerDeserializer.class)
  private Answer answer;

  public TaskDto() {
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

  public Body getQuestion() {
    return body;
  }

  public void setQuestion(Body body) {
    this.body = body;
  }

  public Blank getBlank() {
    return blank;
  }

  public void setBlank(Blank blank) {
    this.blank = blank;
  }

  public Answer getAnswer() {
    return answer;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    TaskDto taskDto = (TaskDto) o;
    return Objects.equals(id, taskDto.id) && Objects.equals(name, taskDto.name) && Objects.equals(description, taskDto.description) && Objects.equals(body, taskDto.body) && Objects.equals(blank, taskDto.blank) && Objects.equals(answer, taskDto.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, body, blank, answer);
  }

  @Override
  public String toString() {
    return "TaskDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", question=" + body +
      ", blank=" + blank +
      ", answer=" + answer +
      '}';
  }
}
