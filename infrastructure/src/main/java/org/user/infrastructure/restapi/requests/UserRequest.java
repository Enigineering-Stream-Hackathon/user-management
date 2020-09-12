package org.user.infrastructure.restapi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.user.domain.commands.UserCommand;
import org.user.domain.entities.Role;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  private String userName;
  private String name;
  private Role role;
  private String password;

  public UserCommand toCommand() {
    return new UserCommand(userName, name, role, password);
  }
}
