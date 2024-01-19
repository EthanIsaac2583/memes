package kz.ruanjian.memed.util;

public enum LogbackMarker {

  Internal("APP_INTERNAL");

  public final String label;

  LogbackMarker(String label) {
    this.label = label;
  }
}
