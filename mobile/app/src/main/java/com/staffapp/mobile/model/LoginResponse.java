package com.staffapp.mobile.model;

public class LoginResponse {
    private boolean loggedIn;

    public LoginResponse(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
