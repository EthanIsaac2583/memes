package kz.ruanjian.memed.pojo.answer;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class SingleChoiceAnswer extends Answer {

  @NotEmpty
  private String key;

  public SingleChoiceAnswer() {
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    SingleChoiceAnswer that = (SingleChoiceAnswer) o;
    return Objects.equals(key, that.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), key);
  }

  @Override
  public String toString() {
    return "SingleChoiceAnswer{" +
      "key='" + key + '\'' +
      ", type=" + type +
      '}';
  }
}
