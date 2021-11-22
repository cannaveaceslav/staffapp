package com.staffapp.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
public class Home {

   @GetMapping(path = "/home")
  public String getHomePage() {
     System.out.println("===========================================");
    return "";
  }
     @GetMapping(path = "/")
  public String getHomePage2() {
     System.out.println("===========================================");
    return "";
  }
}
