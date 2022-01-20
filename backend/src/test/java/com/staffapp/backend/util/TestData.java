package com.staffapp.backend.util;

import com.staffapp.backend.model.User;
import com.staffapp.backend.model.UserRole;

public class TestData {

  public final static String existingEmail = "admin";
  public final static String firstName = "TestFirstName";
  public final static String lastName = "TestFirstName";
  public final static String email = "testmail@mail.ru";
  public final static String password = "123456";
  public final static UserRole userRole = UserRole.USER;

  public final static User testUser = new User(firstName, lastName, email, password, userRole);
}
