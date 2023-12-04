package kz.ruanjian.memed.util;

import java.util.Objects;

public class Item<T> {

  private T content;
  private boolean hasNext;
  private boolean hasPrevious;

  public Item() {
  }

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public boolean isHasPrevious() {
    return hasPrevious;
  }

  public void setHasPrevious(boolean hasPrevious) {
    this.hasPrevious = hasPrevious;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Item<?> item = (Item<?>) o;
    return hasNext==item.hasNext && hasPrevious==item.hasPrevious && Objects.equals(content, item.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, hasNext, hasPrevious);
  }

  @Override
  public String toString() {
    return "Item{" +
      "content=" + content +
      ", hasNext=" + hasNext +
      ", hasPrevious=" + hasPrevious +
      '}';
  }
}
