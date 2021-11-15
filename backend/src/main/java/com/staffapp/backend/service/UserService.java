package com.staffapp.backend.service;

import com.staffapp.backend.model.User;
import com.staffapp.backend.repository.UserRepository;
import com.staffapp.backend.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ConfirmationTokenService confirmationTokenService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
  }

  public String signUpUser(User user) {
    boolean userExists = userRepository
            .findByEmail(user.getEmail())
            .isPresent();

    if (userExists) {
      // TODO check of attributes are the same and
      // TODO if email not confirmed send confirmation email.

      throw new IllegalStateException("email already taken");
    }

    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    userRepository.save(user);

    String token = UUID.randomUUID().toString();

    ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
    );
    confirmationTokenService.saveConfirmationToken(confirmationToken);

    // TODO: 11/8/2021 send email
    return token;

  }

  public void enableUser(String userName){
    userRepository.updateUserEnable(userName);
  }
}
