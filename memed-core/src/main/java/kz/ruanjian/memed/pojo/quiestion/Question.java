package kz.ruanjian.memed.pojo.quiestion;

import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.QuestionType;

import java.util.Objects;

public class Question {

  @NotNull
  protected QuestionType type;

  public Question() {
  }

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Question question = (Question) o;
    return type==question.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "Question{" +
      "type=" + type +
      '}';
  }
}
