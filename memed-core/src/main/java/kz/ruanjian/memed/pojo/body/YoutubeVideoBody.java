package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class YoutubeVideoBody extends Body {

  @NotEmpty
  private String markup;

  public YoutubeVideoBody() {
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
    return "YouTubeVideoQuestion{" +
      "markup='" + markup + '\'' +
      ", type=" + type +
      '}';
  }
}
