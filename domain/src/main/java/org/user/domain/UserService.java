package org.user.domain;

import lombok.AllArgsConstructor;
import org.user.domain.commands.UserCommand;

@AllArgsConstructor
public class UserService {

  private final UserRepository repository;

  public void create(UserCommand command) {
    repository.save(command.toUser());
  }

}
