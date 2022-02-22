package com.staffapp.backend.controller.Login;

import com.staffapp.backend.model.LoginRequest.LoginRequest;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        log.info("User authenticated");
        return true;
    }

    @PostMapping(path = "/validate", consumes = "application/json")
    @ApiOperation("Method returns a response with the map with key=user and value=user from DB")
    public ResponseEntity<Response> getUserJson(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("user", userService.loadUserByUsernameAndPassword(loginRequest.getEmail(), loginRequest.getPassword())))
                        .message("User retrieved 1")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }



//  @PostMapping(path = "/validate", consumes = "application/x-www-form-urlencoded")
//  @ApiOperation("Method returns a response with the map with key=user and value=user from DB")
//  public ResponseEntity<Response> getUser(@RequestParam(name="email") String email,
//                                          @RequestParam(name="password")  String password)  {
//    return ResponseEntity.ok(
//            Response.builder()
//                    .timeStamp(now())
//                    .data(Collections.singletonMap("user", userService.loadUserByUsernameAndPassword(email,password)))
//                    .message("User retrieved")
//                    .status(OK)
//                    .statusCode(OK.value())
//                    .build()
//    );
//  }
}
