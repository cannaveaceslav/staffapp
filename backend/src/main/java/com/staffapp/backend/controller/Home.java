package com.staffapp.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/")
@AllArgsConstructor
public class Home {



  @GetMapping("/home")
  public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                         Model model) {
    model.addAttribute("name");
    return "greeting";
  }
}
