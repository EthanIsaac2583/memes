package kz.ruanjian.memed.pojo.body;

import kz.ruanjian.memed.pojo.BodyType;

public class PlainTextBody extends Body {

  public PlainTextBody() {
  }

  private PlainTextBody(Builder builder) {
    type = builder.type;
    text = builder.text;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private BodyType type;
    private String text;

    public Builder type(BodyType type) {
      this.type = type;
      return this;
    }

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public PlainTextBody build() {
      return new PlainTextBody(this);
    }
  }
}
