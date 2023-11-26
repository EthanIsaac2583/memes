package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class PlainTextBody extends Body {

  @NotEmpty
  private String text;

  public PlainTextBody() {
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
    if (!super.equals(o)) return false;
    PlainTextBody that = (PlainTextBody) o;
    return Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), text);
  }

  @Override
  public String toString() {
    return "PlainTextBody{" +
      "text='" + text + '\'' +
      ", type=" + type +
      '}';
  }
}
