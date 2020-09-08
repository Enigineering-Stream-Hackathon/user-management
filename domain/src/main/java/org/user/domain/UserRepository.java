package org.user.domain;

import org.user.domain.entities.User;

public interface UserRepository {

  void save(User user);
}
