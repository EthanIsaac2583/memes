export type Item<T> = {
  content: T;
  totalItems: number;
  number: number;
  hasNext: boolean;
  hasPrevious: boolean;
  last: boolean;
}
