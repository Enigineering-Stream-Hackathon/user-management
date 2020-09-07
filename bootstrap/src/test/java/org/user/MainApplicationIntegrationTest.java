package org.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.user.infrastructure.repository.FluentJdbcRepository;
import org.user.infrastructure.restapi.UserController;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class MainApplicationIntegrationTest {

  @Autowired
  private UserController userController;

  @Autowired
  private FluentJdbcRepository fluentJdbcRepository;

  @Test
  public void should_create_an_controller() {
    assertThat(userController).isNotNull();
  }

  @Test
  public void should_create_an_repository() {
    assertThat(fluentJdbcRepository).isNotNull();
  }
}
