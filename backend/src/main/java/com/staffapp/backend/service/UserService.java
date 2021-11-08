package com.staffapp.backend.service;

import com.staffapp.backend.model.User;
import com.staffapp.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
  }

  public String signUpUser(User user){
    boolean userExists =  userRepository
            .findByEmail(user.getEmail())
            .isPresent();

    if (userExists){
      throw new IllegalStateException("Email already taken");
    }
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    user.setEnabled(true);
    userRepository.save(user);
    //TODO  send confirmation token
    return "it works";

  }
}
