package org.user.infrastructure.repository;

import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.user.domain.UserRepository;
import org.user.domain.entities.Role;
import org.user.domain.entities.User;

@Repository
public class FluentJdbcRepository implements UserRepository {

  @Autowired
  private FluentJdbc fluentJdbc;

  @Override
  public void save(User user) {
    fluentJdbc.query()
        .update("insert into T_USER values(?, ?, ?, ?)")
        .params(user.getId(), user.getName(), user.getRole(), user.getPassword())
        .run();
  }

  @Override
  public User getByUserName(String userName) {
    return fluentJdbc.query().select("select * from T_USER where ID = ?")
        .params(userName)
        .firstResult(it -> new User(
            it.getString("ID"),
            it.getString("NAME"),
            Role.valueOf(it.getString("ROLES")),
            it.getString("PASSWORD")
        )).orElse(null);
  }

}
