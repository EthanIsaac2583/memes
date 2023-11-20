package kz.ruanjian.memed.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.deserializer.AnswerDeserializer;

import java.util.Objects;

public class LeadQuizAnswerProvideDto {

  private Long id;

  @Valid
  @NotNull
  @JsonDeserialize(using = AnswerDeserializer.class)
  private Answer answer;

  public LeadQuizAnswerProvideDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    LeadQuizAnswerProvideDto that = (LeadQuizAnswerProvideDto) o;
    return Objects.equals(id, that.id) && Objects.equals(answer, that.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, answer);
  }

  @Override
  public String toString() {
    return "LeadQuizAnswerProvideDto{" +
      "id=" + id +
      ", answer=" + answer +
      '}';
  }
}
