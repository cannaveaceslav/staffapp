package com.staffapp.mobile.model;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(Long id, String firstName, String lastName, String email, String password, UserRole userRole, Boolean locked, Boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
