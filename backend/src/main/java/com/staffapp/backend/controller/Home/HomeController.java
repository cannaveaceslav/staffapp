package com.staffapp.backend.controller.Home;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"", "/", "home"})
@AllArgsConstructor
@Api("Controller for main page")
public class HomeController {

@ApiOperation("Show user main page. Return view 'greeting.html'")
  @GetMapping
  public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                         Model model) {
    model.addAttribute("name");
    return "greeting";
  }


}
