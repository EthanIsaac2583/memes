package kz.ruanjian.memed.dto;

import java.util.UUID;

public class VisitDto {

  private UUID id;

  private String userAgent;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
}
