package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.pojo.BodyType;

import java.util.Objects;

public class Body {

  @NotNull
  protected BodyType type;

  protected String text;

  public Body() {
  }

  public BodyType getType() {
    return type;
  }

  public void setType(BodyType type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Body body = (Body) o;
    return type==body.type && Objects.equals(text, body.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, text);
  }

  @Override
  public String toString() {
    return "Body{" +
            "type=" + type +
            ", text='" + text + '\'' +
            '}';
  }
}
