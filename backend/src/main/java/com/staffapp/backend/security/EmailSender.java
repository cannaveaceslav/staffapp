package com.staffapp.backend.security;

public interface EmailSender {
  void send (String to, String email);
}
