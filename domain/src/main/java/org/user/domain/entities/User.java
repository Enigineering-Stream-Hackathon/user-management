package org.user.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

  private String id;
  private String name;
  private Role role;
  private String password;
}
