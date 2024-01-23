package kz.ruanjian.memed.dto;

import kz.ruanjian.memed.model.Lead;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    AuthResponseDto that = (AuthResponseDto) o;
    return Objects.equals(token, that.token) && Objects.equals(lead, that.lead);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, lead);
  }
}
