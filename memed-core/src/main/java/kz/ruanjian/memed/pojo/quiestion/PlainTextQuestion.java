package kz.ruanjian.memed.pojo.quiestion;

import java.util.Objects;

public class PlainTextQuestion extends Question {

  private String question;

  public PlainTextQuestion() {
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    PlainTextQuestion that = (PlainTextQuestion) o;
    return Objects.equals(question, that.question);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), question);
  }
}
