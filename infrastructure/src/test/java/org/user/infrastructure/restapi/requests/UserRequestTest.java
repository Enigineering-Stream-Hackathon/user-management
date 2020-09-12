package org.user.infrastructure.restapi.requests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.user.domain.entities.Role.ADMIN;

import lombok.val;
import org.junit.Test;
import org.user.domain.commands.UserCommand;

public class UserRequestTest {

  @Test
  public void should_convert_to_command_when_toCommand_is_invoked() {
    val request = new UserRequest("john.wick", "John Wick", ADMIN, "password");

    val command = request.toCommand();

    assertThat(command).isEqualToComparingFieldByField(
        new UserCommand("john.wick", "John Wick", ADMIN, "password"));
  }
}
