package kz.ruanjian.memed.config;

import kz.ruanjian.memed.respository.LeadRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {

  @Bean
  public UserDetailsService userDetailsService(LeadRepository leadRepository) {
    return username -> leadRepository
      .findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("Lead not found"));
  }
}
