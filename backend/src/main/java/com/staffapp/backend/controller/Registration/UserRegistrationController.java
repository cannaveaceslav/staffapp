package com.staffapp.backend.controller.Registration;

import com.staffapp.backend.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
@Api("Controller for user registration page")
public class UserRegistrationController {

  private final RegistrationService registrationService;

  @GetMapping
  @ApiOperation("Get registration page. Returns 'register.html' view")
  public String register() {
    return "register";
  }

  @PostMapping
  @ResponseBody
  @ApiOperation("Get data from user request and send it to registration service. Returns token so far")
  public String register(@RequestBody RegistrationRequest registrationRequest) {
    return registrationService.register(registrationRequest);
  }

  @GetMapping(path = "confirm")
  @ResponseBody
  @ApiOperation("Get token from request (request usually shoudl go from emeil link) and send it to " +
          "registrationService to validate it. Returns message that token was confirmed")
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}
