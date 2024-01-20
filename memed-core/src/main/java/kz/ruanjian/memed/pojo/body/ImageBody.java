package kz.ruanjian.memed.pojo.body;

import jakarta.validation.constraints.NotEmpty;
import kz.ruanjian.memed.pojo.BodyType;

import java.util.Objects;

public class ImageBody extends Body {

  @NotEmpty
  private String url;

  public ImageBody() {
  }

  private ImageBody(Builder builder) {
    type = builder.type;
    text = builder.text;
    url = builder.url;
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

  public static final class Builder {

    private BodyType type;
    private String text;
    private String url;

    public Builder type(BodyType type) {
      this.type = type;
      return this;
    }

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public ImageBody build() {
      return new ImageBody(this);
    }
  }
}
