package org.user.infrastructure.restapi;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.user.domain.UserService;
import org.user.infrastructure.restapi.requests.UserRequest;

@Slf4j
@RestController
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping(value = "/user", consumes = "application/json")
  public ResponseEntity create(@RequestBody UserRequest request) {
    log.info("Request received to create user {}", request.getId());
    service.create(request.toCommand());
    return status(CREATED).build();
  }

}
