package com.staffapp.backend.service;

import com.staffapp.backend.controller.RegistrationRequest;
import com.staffapp.backend.model.User;
import com.staffapp.backend.model.UserRole;
import com.staffapp.backend.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final UserService userService;
  private final EmailValidator emailValidator;

  public String register(RegistrationRequest registrationRequest) {
    boolean isValidEmail = emailValidator.test(registrationRequest.getEmail());
    if (!isValidEmail) {
      throw new IllegalStateException("email is not valid");
    }
    return userService.signUpUser(new User(registrationRequest.getFirstName(),
                                           registrationRequest.getLastName(),
                                           registrationRequest.getEmail(),
                                           registrationRequest.getPassword(),
                                           UserRole.USER
                                  )
    );
  }
}
