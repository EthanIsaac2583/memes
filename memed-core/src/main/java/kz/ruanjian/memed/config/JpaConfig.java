package kz.ruanjian.memed.config;

import kz.ruanjian.memed.respository.singularrepository.SingularRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "kz.ruanjian.memed.respository",
  repositoryBaseClass = SingularRepositoryImpl.class)
public class JpaConfig {
}
