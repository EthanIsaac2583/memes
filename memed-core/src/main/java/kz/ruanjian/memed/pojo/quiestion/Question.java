package kz.ruanjian.memed.pojo.quiestion;

import kz.ruanjian.memed.pojo.QuestionType;

import java.util.Objects;

public class Question {

  private QuestionType type;

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
}
