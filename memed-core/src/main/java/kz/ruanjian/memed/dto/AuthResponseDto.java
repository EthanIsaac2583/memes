package kz.ruanjian.memed.dto;

import kz.ruanjian.memed.model.Lead;

public class AuthResponseDto {

  private String token;
  private Lead lead;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Lead getLead() {
    return lead;
  }

  public void setLead(Lead lead) {
    this.lead = lead;
  }
}
