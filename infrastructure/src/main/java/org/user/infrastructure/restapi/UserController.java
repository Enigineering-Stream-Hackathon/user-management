package org.user.infrastructure.restapi;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.user.domain.UserService;
import org.user.domain.entities.User;
import org.user.infrastructure.restapi.requests.UserRequest;

@Slf4j
@RestController
@Tag(name = "User Controller", description = "Controller to create and get user")
public class UserController {

  @Autowired
  private UserService service;

  @Operation(summary = "Create user with username and role")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema())}),
      @ApiResponse(responseCode = "500", description = "Server error",
          content = @Content)})
  @PostMapping(value = "/user", consumes = "application/json")
  public ResponseEntity create(@RequestBody UserRequest request) {
    log.info("Request received to create user {}", request.getUserName());
    service.create(request.toCommand());
    return status(CREATED).build();
  }

  @Operation(summary = "Get user details for the username")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User retrieved successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content)})
  @GetMapping(value = "/user")
  public ResponseEntity<User> findByUserName(@RequestParam(value = "userName") String userName) {
    log.info("Request received to get user");
    return ofNullable(service.findByUserName(userName))
        .map(it -> status(OK).body(it))
        .orElse(status(NOT_FOUND).build());

  }
}
