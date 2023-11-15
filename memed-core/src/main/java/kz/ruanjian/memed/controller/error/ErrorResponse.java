package kz.ruanjian.memed.controller.error;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ErrorResponse {

  private int statusCode;
  private ZonedDateTime timestamp;
  private String message;
  private Map<String, String> fieldErrors;
  private Set<String> globalErrors;

  public ErrorResponse() {
  }

  private ErrorResponse(Builder builder) {
    statusCode = builder.statusCode;
    timestamp = builder.timestamp;
    message = builder.message;
    fieldErrors = builder.fieldErrors;
    globalErrors = builder.globalErrors;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, String> getFieldErrors() {
    return fieldErrors;
  }

  public void setFieldErrors(Map<String, String> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

  public Set<String> getGlobalErrors() {
    return globalErrors;
  }

  public void setGlobalErrors(Set<String> globalErrors) {
    this.globalErrors = globalErrors;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    ErrorResponse that = (ErrorResponse) o;
    return statusCode==that.statusCode && Objects.equals(message, that.message) && Objects.equals(fieldErrors, that.fieldErrors) && Objects.equals(globalErrors, that.globalErrors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, message, fieldErrors, globalErrors);
  }

  @Override
  public String toString() {
    return "ErrorResponse{" +
      "statusCode=" + statusCode +
      ", timestamp=" + timestamp +
      ", message='" + message + '\'' +
      ", fieldErrors=" + fieldErrors +
      ", globalErrors=" + globalErrors +
      '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private int statusCode;
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private String message = "";
    private Map<String, String> fieldErrors = new HashMap<>();
    private Set<String> globalErrors = new HashSet<>();

    public Builder statusCode(int statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    public Builder timestamp(ZonedDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder fieldErrors(Map<String, String> fieldErrors) {
      this.fieldErrors = fieldErrors;
      return this;
    }

    public Builder globalErrors(Set<String> globalErrors) {
      this.globalErrors = globalErrors;
      return this;
    }

    public ErrorResponse build() {
      return new ErrorResponse(this);
    }
  }
}
