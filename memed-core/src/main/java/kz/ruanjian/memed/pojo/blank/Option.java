package kz.ruanjian.memed.pojo.blank;

import java.util.Objects;

public class Option {

  private String key;
  private String value;

  public Option() {
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Option option = (Option) o;
    return Objects.equals(key, option.key) && Objects.equals(value, option.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    return "Option{" +
      "key='" + key + '\'' +
      ", value='" + value + '\'' +
      '}';
  }
}
