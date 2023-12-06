package kz.ruanjian.memed.respository.singularrepository;

import java.util.Objects;

public class Single<T> {

  private T content;

  public Single() {
  }

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Single<?> single = (Single<?>) o;
    return Objects.equals(content, single.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content);
  }

  @Override
  public String toString() {
    return "Single{" +
      "content=" + content +
      '}';
  }
}
