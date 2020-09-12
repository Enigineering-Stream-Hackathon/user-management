package org.user.infrastructure.restapi;

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
import org.user.domain.UserService;
import org.user.domain.commands.UserCommand;
import org.user.infrastructure.restapi.requests.UserRequest;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  @Mock
  private UserService service;

  @InjectMocks
  private UserController controller;

  @Test
  public void should_invoke_service_to_create_user() {
    val argumentCaptor = ArgumentCaptor.forClass(UserCommand.class);
    val request = new UserRequest("john.wick", "John Wick", ADMIN, "password");

    controller.create(request);

    verify(service).create(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(
        new UserCommand("john.wick", "John Wick", ADMIN, "password"));

  }


}
