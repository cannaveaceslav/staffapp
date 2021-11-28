package com.staffapp.backend.controller;

import com.staffapp.backend.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class UserRegistrationController {

  private final RegistrationService registrationService;

  @GetMapping
  public String register() {
    return "register";
  }

  @PostMapping
  @ResponseBody
  public String register(@RequestBody RegistrationRequest registrationRequest) {
    return registrationService.register(registrationRequest);
  }

  @GetMapping(path = "confirm")
  @ResponseBody
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}
