package kz.ruanjian.memed.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.deserializer.AnswerDeserializer;

import java.util.Objects;

public class AnswerDto {

  private Long questionId;

  @Valid
  @NotNull
  @JsonDeserialize(using = AnswerDeserializer.class)
  private Answer answer;

  public AnswerDto() {
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
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
    AnswerDto answerDto = (AnswerDto) o;
    return Objects.equals(questionId, answerDto.questionId) && Objects.equals(answer, answerDto.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionId, answer);
  }

  @Override
  public String toString() {
    return "AnswerDto{" +
      "questionId=" + questionId +
      ", answer=" + answer +
      '}';
  }
}
