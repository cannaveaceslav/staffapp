package com.staffapp.backend.controller.Login;

import com.staffapp.backend.model.Response;
import com.staffapp.backend.model.User;
import com.staffapp.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/login")
@Api("Controller to work with login page")
@Slf4j
public class LoginController {

  private final UserService userService;

  @GetMapping(produces = "application/json")
  @RequestMapping({"/validateLogin"})
  public boolean validateLogin() {
    //todo fetch user from userService
    log.info("User authenticated");
    return true;
  }

  @GetMapping("/validate/{email}/{password}")
  @ApiOperation("Method returns a response with the map with key=user and value=user from DB")
  public ResponseEntity<Response> getEmployees(@PathVariable("email") String email,
                                               @PathVariable("password") String password) throws InterruptedException {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("user", userService.loadUserByUsernameAndPassword(email,password)))
                    .message("User retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }
}
