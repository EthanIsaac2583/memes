package kz.ruanjian.memed.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class NextQuestionDto {

  @NotNull
  private Long quizId;

  public NextQuestionDto() {
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    NextQuestionDto that = (NextQuestionDto) o;
    return Objects.equals(quizId, that.quizId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quizId);
  }

  @Override
  public String toString() {
    return "NextQuestionDto{" +
      "quizId=" + quizId +
      '}';
  }
}
