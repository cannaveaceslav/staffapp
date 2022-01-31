package com.staffapp.backend.service.user;

import com.staffapp.backend.model.User;
import com.staffapp.backend.model.UserRole;
import com.staffapp.backend.service.registration.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class UserServiceTest {

  @Autowired
  private  UserService userService;
  @Autowired
  private  ConfirmationTokenService confirmationTokenService;

  private User testUser;

  @BeforeEach
  void setUpTestData() {
    testUser = User.builder().firstName("TestFirstName")
            .lastName("TestLastName")
            .email("testemail@mail.ru")
            .password("123456")
            .userRole(UserRole.USER)
            .enabled(false)
            .build();
    log.info("TestUser  with email [{}]", testUser.getEmail() + " created");
    userService.signUpUser(testUser);
  }

  @AfterEach
  void deleteTestData() {
    confirmationTokenService.deleteTokenByUserId(testUser.getId());
    userService.deleteUser(testUser);
    log.info("TestUser  with email [{}] .", testUser.getEmail() + " deleted");
  }

  @Test
  void assertThatThrownWhenUserEnabled() {
    userService.enableUser(testUser.getEmail());
    IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
      userService.signUpUser(testUser);
    }, "email already taken");
    assertEquals("email already taken", thrown.getMessage());
  }


  @Test
  public void assertThatUserExists() {
    assertNotNull(userService.loadUserByUsername(testUser.getEmail()));
  }


  @Test
  public void assertThatUserIsNotEnabled() {
    assertFalse(userService.loadUserByUsername(testUser.getEmail()).isEnabled());
  }


  @Test
  public void assertThatUserBecomesEnabled() {
    userService.enableUser(testUser.getEmail());
    assertTrue(userService.loadUserByUsername(testUser.getEmail()).isEnabled());
  }

  @Test
  public void assertThatUserWithEmailAndPasswordExists() {
    assertNotNull(userService.loadUserByUsernameAndPassword(testUser.getEmail(), testUser.getPassword()));
  }


}