package kz.ruanjian.memed.pojo.blank;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.Option;

import java.util.Objects;
import java.util.Set;

public class SingleChoiceBlank extends Blank {

  @NotNull
  @NotEmpty
  @Valid
  private Set<Option> options;

  public SingleChoiceBlank() {
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
    SingleChoiceBlank that = (SingleChoiceBlank) o;
    return Objects.equals(options, that.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), options);
  }

  @Override
  public String toString() {
    return "SingleChoiceBlank{" +
      "options=" + options +
      ", type=" + type +
      '}';
  }
}
