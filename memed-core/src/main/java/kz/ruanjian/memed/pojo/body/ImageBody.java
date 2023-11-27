package kz.ruanjian.memed.pojo.body;

import java.util.Objects;

public class ImageBody extends Body {

  private String text;
  private String url;

  public ImageBody() {
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    if (!super.equals(o)) return false;
    ImageBody imageBody = (ImageBody) o;
    return Objects.equals(text, imageBody.text) && Objects.equals(url, imageBody.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), text, url);
  }

  @Override
  public String toString() {
    return "ImageBody{" +
      "text='" + text + '\'' +
      ", url='" + url + '\'' +
      ", type=" + type +
      '}';
  }
}
