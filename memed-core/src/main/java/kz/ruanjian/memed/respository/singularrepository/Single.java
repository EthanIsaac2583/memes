package kz.ruanjian.memed.respository.singularrepository;

import java.util.Objects;

public class Single<T> {

  private T content;

  private boolean hasNext;
  private boolean hasPrevious;
  private int size;

  public Single() {
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

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Single<?> single = (Single<?>) o;
    return hasNext==single.hasNext && hasPrevious==single.hasPrevious && size==single.size && Objects.equals(content, single.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, hasNext, hasPrevious, size);
  }

  @Override
  public String toString() {
    return "Single{" +
      "content=" + content +
      ", hasNext=" + hasNext +
      ", hasPrevious=" + hasPrevious +
      ", size=" + size +
      '}';
  }
}
