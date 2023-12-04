package kz.ruanjian.memed.util;

import java.util.Objects;

public class Item<T> {

  private T content;
  private int totalItems;
  private int number;
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

  public int getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(int totalItems) {
    this.totalItems = totalItems;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
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
    return totalItems==item.totalItems && number==item.number && hasNext==item.hasNext && hasPrevious==item.hasPrevious && Objects.equals(content, item.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalItems, number, hasNext, hasPrevious);
  }

  @Override
  public String toString() {
    return "Item{" +
      "content=" + content +
      ", totalItems=" + totalItems +
      ", number=" + number +
      ", hasNext=" + hasNext +
      ", hasPrevious=" + hasPrevious +
      '}';
  }
}
