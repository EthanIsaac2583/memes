package kz.ruanjian.memed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "memed")
public class MemedProperties {

  private Integer gradeMin;
  private Integer gradeMax;
  private Long securityExpirationInMs;
  private String securitySecret;

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

  public Long getSecurityExpirationInMs() {
    return securityExpirationInMs;
  }

  public void setSecurityExpirationInMs(Long securityExpirationInMs) {
    this.securityExpirationInMs = securityExpirationInMs;
  }

  public String getSecuritySecret() {
    return securitySecret;
  }

  public void setSecuritySecret(String securitySecret) {
    this.securitySecret = securitySecret;
  }
}
