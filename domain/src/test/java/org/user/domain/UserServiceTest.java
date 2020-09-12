package org.user.domain;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.user.domain.entities.Role.ADMIN;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.user.domain.commands.UserCommand;
import org.user.domain.entities.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @Mock
  private UserRepository repository;

  @InjectMocks
  private UserService service;

  @Test
  public void should_call_repository_to_create_user() {
    val argumentCaptor = ArgumentCaptor.forClass(User.class);
    val command = new UserCommand("john.wick", "John Wick", ADMIN, "password");

    service.create(command);

    verify(repository).save(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(
        new User("john.wick", "John Wick", ADMIN, "password"));
  }
}
