package com.staffapp.backend.controller.Layout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/layout")
public class LayoutController {

  @GetMapping
  public String showLayout() {
    return "layout";
  }

}
