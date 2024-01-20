package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotEmpty;
import kz.ruanjian.memed.pojo.BodyType;

import java.util.Objects;

public class YoutubeVideoBody extends Body {

  @NotEmpty
  private String markup;

  public YoutubeVideoBody() {
  }

  private YoutubeVideoBody(Builder builder) {
    type = builder.type;
    text = builder.text;
    markup = builder.markup;
  }

  public String getMarkup() {
    return markup;
  }

  public void setMarkup(String markup) {
    this.markup = markup;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    YoutubeVideoBody that = (YoutubeVideoBody) o;
    return Objects.equals(markup, that.markup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), markup);
  }

  @Override
  public String toString() {
    return "YoutubeVideoBody{" +
            "markup='" + markup + '\'' +
            ", type=" + type +
            ", text='" + text + '\'' +
            '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private BodyType type;
    private String text;
    private String markup;

    public Builder type(BodyType type) {
      this.type = type;
      return this;
    }

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Builder markup(String markup) {
      this.markup = markup;
      return this;
    }

    public YoutubeVideoBody build() {
      return new YoutubeVideoBody(this);
    }
  }
}
