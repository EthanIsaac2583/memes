export class ApplicationLocalStorage {

  private static readonly PREFIX = 'memed_app__'

  public static getItem(key: string): string | null {
    const fullKey = ApplicationLocalStorage.buildItemFullKey(key);
    return window.localStorage.getItem(fullKey);
  }

  public static setItem(key: string, value: string): void {
    const fullKey = ApplicationLocalStorage.buildItemFullKey(key);
    window.localStorage.setItem(fullKey, value);
  }

  public static removeItem(key: string): void {
    const fullKey = ApplicationLocalStorage.buildItemFullKey(key);
    window.localStorage.removeItem(fullKey);
  }

  private static buildItemFullKey(name: string) {
    return ApplicationLocalStorage.PREFIX + name;
  }
}

export enum StorageKey {
  VisitId = 'visit_id'
}
