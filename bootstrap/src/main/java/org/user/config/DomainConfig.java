package org.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.user.domain.UserService;
import org.user.infrastructure.repository.FluentJdbcRepository;

@Configuration
public class DomainConfig {

  private final FluentJdbcRepository repository;

  public DomainConfig(FluentJdbcRepository repository) {
    this.repository = repository;
  }

  @Bean
  public UserService schoolService() {
    return new UserService(repository);
  }
}
