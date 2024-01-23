package kz.ruanjian.memed.dto;

import kz.ruanjian.memed.model.Lead;

import java.util.Objects;

public class AuthResponseDto {

  private String token;
  private Lead lead;

  public AuthResponseDto() {
  }

  private AuthResponseDto(Builder builder) {
    token = builder.token;
    lead = builder.lead;
  }

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

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String token;
    private Lead lead;

    public Builder token(String token) {
      this.token = token;
      return this;
    }

    public Builder lead(Lead lead) {
      this.lead = lead;
      return this;
    }

    public AuthResponseDto build() {
      return new AuthResponseDto(this);
    }
  }
}
