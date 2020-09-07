package org.user.domain.entities;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

  private String id;
  private String name;
  private List<Role> roles;
  private String password;
}
