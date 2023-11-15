package kz.ruanjian.memed.pojo.answer;

import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.BlankType;

import java.util.Objects;

public class Answer {

  @NotNull
  protected BlankType type;

  public Answer() {
  }

  public BlankType getType() {
    return type;
  }

  public void setType(BlankType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Answer answer = (Answer) o;
    return type==answer.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "Answer{" +
      "type=" + type +
      '}';
  }
}
