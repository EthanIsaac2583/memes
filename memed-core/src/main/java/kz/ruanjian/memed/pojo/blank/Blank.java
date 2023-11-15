package kz.ruanjian.memed.pojo.blank;

import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.BlankType;

import java.util.Objects;

public class Blank {

  @NotNull
  protected BlankType type;

  public Blank() {
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
    Blank blank = (Blank) o;
    return type==blank.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "Blank{" +
      "type=" + type +
      '}';
  }
}
