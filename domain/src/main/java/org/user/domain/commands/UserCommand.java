package org.user.domain.commands;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.user.domain.entities.Role;
import org.user.domain.entities.User;

@Getter
@AllArgsConstructor
public class UserCommand {

  private String id;
  private String name;
  private List<Role> roles;
  private String password;

  public User toUser() {
    return new User(id, name, roles, password);
  }
}
