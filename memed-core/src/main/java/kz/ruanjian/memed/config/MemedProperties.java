package kz.ruanjian.memed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "memed")
public class MemedProperties {

  private Integer gradeMin;
  private Integer gradeMax;

  public MemedProperties() {
  }

  public int getGradeMin() {
    return gradeMin;
  }

  public void setGradeMin(int gradeMin) {
    this.gradeMin = gradeMin;
  }

  public int getGradeMax() {
    return gradeMax;
  }

  public void setGradeMax(int gradeMax) {
    this.gradeMax = gradeMax;
  }
}
