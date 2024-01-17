package kz.ruanjian.memed.config;

import kz.ruanjian.memed.security.SecurityAuthenticationEntryPoint;
import kz.ruanjian.memed.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String PRIVATE_ROUTE = "/api/v1/private/**";

  private final SecurityAuthenticationEntryPoint authenticationEntryPoint;
  private final SecurityFilter securityFilter;
  private final AuthenticationProvider authenticationProvider;

  public SecurityConfig(SecurityAuthenticationEntryPoint authenticationEntryPoint,
                        SecurityFilter securityFilter,
                        AuthenticationProvider authenticationProvider) {
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.securityFilter = securityFilter;
    this.authenticationProvider = authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    configureCors(httpSecurity);
    disableCsrf(httpSecurity);
    configureAuthorization(httpSecurity);
    makeSessionStateless(httpSecurity);
    configureAuthentication(httpSecurity);

    httpSecurity.exceptionHandling(exceptionHandler -> exceptionHandler.authenticationEntryPoint(authenticationEntryPoint));

    return httpSecurity.build();
  }

  private void configureAuthentication(HttpSecurity httpSecurity) {
    httpSecurity
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
  }

  private void makeSessionStateless(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
  }

  private void configureAuthorization(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .authorizeHttpRequests(request -> request
        .requestMatchers(PRIVATE_ROUTE).authenticated()
        .anyRequest().permitAll());
  }

  private void disableCsrf(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
  }

  private void configureCors(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors(cors -> cors.configurationSource(request -> {
      CorsConfiguration configuration = new CorsConfiguration();

      configuration.setAllowedOriginPatterns(List.of("*"));
      configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
      configuration.setAllowedHeaders(List.of("*"));
      configuration.setAllowCredentials(true);

      return configuration;
    }));
  }
}
