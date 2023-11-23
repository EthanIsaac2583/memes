package kz.ruanjian.memed.pojo.answer;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.Set;

public class MultipleChoiceAnswer extends Answer {

  @NotEmpty
  private Set<String> keys;

  public MultipleChoiceAnswer() {
  }

  public Set<String> getKeys() {
    return keys;
  }

  public void setKeys(Set<String> keys) {
    this.keys = keys;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    MultipleChoiceAnswer that = (MultipleChoiceAnswer) o;
    return Objects.equals(keys, that.keys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), keys);
  }

  @Override
  public String toString() {
    return "MultipleChoiceAnswer{" +
      "keys=" + keys +
      ", type=" + type +
      '}';
  }
}
