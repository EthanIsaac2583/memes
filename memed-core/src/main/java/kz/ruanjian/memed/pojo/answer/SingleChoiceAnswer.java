package kz.ruanjian.memed.pojo.answer;

import jakarta.validation.constraints.NotEmpty;
import kz.ruanjian.memed.pojo.BlankType;

import java.util.Objects;

public class SingleChoiceAnswer extends Answer {

  @NotEmpty
  private String key;

  public SingleChoiceAnswer() {
  }

  private SingleChoiceAnswer(Builder builder) {
    type = builder.type;
    key = builder.key;
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

  public static final class Builder {

    private BlankType type;
    private String key;

    public Builder type(BlankType type) {
      this.type = type;
      return this;
    }

    public Builder key(String key) {
      this.key = key;
      return this;
    }

    public SingleChoiceAnswer build() {
      return new SingleChoiceAnswer(this);
    }
  }
}
