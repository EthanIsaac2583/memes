package kz.ruanjian.memed.util;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Itemized {

  @NotNull
  private Long quizId;

  private Integer number = 1;

  public Itemized() {
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Itemized itemized = (Itemized) o;
    return Objects.equals(quizId, itemized.quizId) && Objects.equals(number, itemized.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quizId, number);
  }

  @Override
  public String toString() {
    return "Itemized{" +
      "quizId=" + quizId +
      ", number=" + number +
      '}';
  }
}
