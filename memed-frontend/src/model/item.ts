export type Item<T> = {
  item: T;
  totalItems: number;
  number: number;
  hasNext: boolean;
  hasPrevious: boolean;
}
