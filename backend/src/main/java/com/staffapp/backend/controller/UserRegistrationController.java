package com.staffapp.backend.controller;

import com.staffapp.backend.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class UserRegistrationController {

  private final RegistrationService registrationService;

  @GetMapping
  public String register() {
    return "<html>\n" + "<header><title>Welcome</title></header>\n" +
            "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
  }

//  @GetMapping
//  public String register() {
//    return "register";
//  }

  @PostMapping
  public String register(@RequestBody RegistrationRequest registrationRequest) {
    return registrationService.register(registrationRequest);
  }

  @GetMapping(path = "confirm")
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}
