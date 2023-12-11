package kz.ruanjian.memed.util;

import java.util.Objects;

public class Item<T> {

  private T item;
  private int totalItems;
  private int number;
  private boolean hasNext;
  private boolean hasPrevious;

  public Item() {
  }

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
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
    Item<?> item1 = (Item<?>) o;
    return totalItems==item1.totalItems && number==item1.number && hasNext==item1.hasNext && hasPrevious==item1.hasPrevious && Objects.equals(item, item1.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, totalItems, number, hasNext, hasPrevious);
  }

  @Override
  public String toString() {
    return "Item{" +
      "item=" + item +
      ", totalItems=" + totalItems +
      ", number=" + number +
      ", hasNext=" + hasNext +
      ", hasPrevious=" + hasPrevious +
      '}';
  }
}
