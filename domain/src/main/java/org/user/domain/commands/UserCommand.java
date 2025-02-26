package org.user.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.user.domain.entities.Role;
import org.user.domain.entities.User;

@Getter
@AllArgsConstructor
public class UserCommand {

  private String id;
  private String name;
  private Role role;
  private String password;

  public User toUser() {
    return new User(id, name, role, password);
  }
}
