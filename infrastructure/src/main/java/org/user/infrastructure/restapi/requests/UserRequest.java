package org.user.infrastructure.restapi.requests;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.user.domain.commands.UserCommand;
import org.user.domain.entities.Role;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  private String id;
  private String name;
  private List<Role> roles;
  private String password;

  public UserCommand toCommand() {
    return new UserCommand(id, name, roles, password);
  }
}
