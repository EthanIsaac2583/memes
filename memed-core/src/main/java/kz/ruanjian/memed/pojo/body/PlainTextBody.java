package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class PlainTextBody extends Body {

  @NotEmpty
  private String body;

  public PlainTextBody() {
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    PlainTextBody that = (PlainTextBody) o;
    return Objects.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), body);
  }

  @Override
  public String toString() {
    return "PlainTextQuestion{" +
      "body='" + body + '\'' +
      ", type=" + type +
      '}';
  }
}
