package kz.ruanjian.memed.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class AuthDto {

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;

  public AuthDto() {
  }

  private AuthDto(Builder builder) {
    username = builder.username;
    password = builder.password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    AuthDto authDto = (AuthDto) o;
    return Objects.equals(username, authDto.username) && Objects.equals(password, authDto.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String username;
    private String password;

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public AuthDto build() {
      return new AuthDto(this);
    }
  }
}
