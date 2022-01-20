package com.staffapp.backend.service.user;

import com.staffapp.backend.service.registration.ConfirmationTokenService;
import com.staffapp.backend.util.TestData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private ConfirmationTokenService confirmationTokenService;

  @AfterAll
  void tearDown() {
    confirmationTokenService.deleteTokenByUserId(TestData.testUser.getId());
    System.out.println("after test class");
    userService.deleteUser(TestData.testUser);
  }

  @Test
  public void assertThatUserExists() {
    assertNotNull(userService.loadUserByUsername(TestData.existingEmail));
  }


  @Test
  public void assertThatUserIsNotEnabledAndNotLocked() {
    assertTrue(userService.loadUserByUsername(TestData.existingEmail).isEnabled());
  }

  @Test
  public void assertThatUserIsCreated() {
    assertNotNull(userService.signUpUser(TestData.testUser));
  }

  @Test
  public void enableUser() {
  }

  @Test
  public void loadUserByUsernameAndPassword() {
  }


}