package kz.ruanjian.memed.util;

public enum LogbackMarker {

  Internal("APPLICATION_INTERNAL");

  public final String label;

  LogbackMarker(String label) {
    this.label = label;
  }
}
