package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class ImageBody extends Body {

  @NotEmpty
  private String url;

  public ImageBody() {
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
    return Objects.equals(url, imageBody.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), url);
  }
}
