package org.user.domain;

import lombok.AllArgsConstructor;
import org.user.domain.commands.UserCommand;
import org.user.domain.entities.User;

@AllArgsConstructor
public class UserService {

  private final UserRepository repository;

  public void create(UserCommand command) {
    repository.save(command.toUser());
  }

  public User findByUserName(String userName) {
    return repository.getByUserName(userName);
  }

}
