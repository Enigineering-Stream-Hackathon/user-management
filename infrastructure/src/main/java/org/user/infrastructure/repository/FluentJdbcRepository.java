package org.user.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.user.domain.UserRepository;
import org.user.domain.entities.User;

@Repository
public class FluentJdbcRepository implements UserRepository {

  @Autowired
  private FluentJdbc fluentJdbc;

  @Autowired
  private ObjectMapper mapper;

  @SneakyThrows
  @Override
  public void save(User user) {
    fluentJdbc.query()
        .update("insert into T_USER values(?, ?, ?, ?)")
        .params(user.getId(), user.getName(), mapper.writeValueAsString(user.getRoles()),
            user.getPassword())
        .run();
  }

}
