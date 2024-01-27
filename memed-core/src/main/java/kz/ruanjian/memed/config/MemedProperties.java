package kz.ruanjian.memed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "memed")
public class MemedProperties {

  private ApplicationProperties application;
  private SecurityProperties security;

  public ApplicationProperties getApplication() {
    return application;
  }

  public void setApplication(ApplicationProperties application) {
    this.application = application;
  }

  public SecurityProperties getSecurity() {
    return security;
  }

  public void setSecurity(SecurityProperties security) {
    this.security = security;
  }

  public static class ApplicationProperties {

    private Integer gradeMin;
    private Integer gradeMax;

    public Integer getGradeMin() {
      return gradeMin;
    }

    public void setGradeMin(Integer gradeMin) {
      this.gradeMin = gradeMin;
    }

    public Integer getGradeMax() {
      return gradeMax;
    }

    public void setGradeMax(Integer gradeMax) {
      this.gradeMax = gradeMax;
    }
  }

  public static class SecurityProperties {

    private String secret;
    private Long expirationInMs;
    private String adminUsername;
    private String adminPassword;

    public String getSecret() {
      return secret;
    }

    public void setSecret(String secret) {
      this.secret = secret;
    }

    public Long getExpirationInMs() {
      return expirationInMs;
    }

    public void setExpirationInMs(Long expirationInMs) {
      this.expirationInMs = expirationInMs;
    }

    public String getAdminUsername() {
      return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
      this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
      return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
      this.adminPassword = adminPassword;
    }
  }
}
