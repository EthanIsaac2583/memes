package kz.ruanjian.memed.config;

import kz.ruanjian.memed.filter.DebuggingRequestLogger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  private static final String ALL_ROUTES = "/*";

  @Bean
  public FilterRegistrationBean<DebuggingRequestLogger> requestLoggerFilterRegistrationBean() {
    FilterRegistrationBean<DebuggingRequestLogger> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new DebuggingRequestLogger());
    registrationBean.addUrlPatterns(ALL_ROUTES);
    return registrationBean;
  }
}
