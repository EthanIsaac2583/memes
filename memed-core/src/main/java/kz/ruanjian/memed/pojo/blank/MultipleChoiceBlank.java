package kz.ruanjian.memed.pojo.blank;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import kz.ruanjian.memed.pojo.Option;

import java.util.Objects;
import java.util.Set;

public class MultipleChoiceBlank extends Blank {

  @NotEmpty
  @Valid
  private Set<Option> options;

  public MultipleChoiceBlank() {
  }

  public Set<Option> getOptions() {
    return options;
  }

  public void setOptions(Set<Option> options) {
    this.options = options;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    MultipleChoiceBlank that = (MultipleChoiceBlank) o;
    return Objects.equals(options, that.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), options);
  }

  @Override
  public String toString() {
    return "MultipleChoiceBlank{" +
      "options=" + options +
      ", type=" + type +
      '}';
  }
}
