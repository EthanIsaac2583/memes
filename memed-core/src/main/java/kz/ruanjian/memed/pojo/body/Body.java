package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.BodyType;

import java.util.Objects;

public class Body {

  @NotNull
  protected BodyType type;

  public Body() {
  }

  public BodyType getType() {
    return type;
  }

  public void setType(BodyType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Body body = (Body) o;
    return type==body.type;
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