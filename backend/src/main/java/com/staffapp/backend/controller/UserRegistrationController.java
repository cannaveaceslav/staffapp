package com.staffapp.backend.controller;

import com.staffapp.backend.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class UserRegistrationController {

  private final RegistrationService registrationService;

 /* @GetMapping
  public String register() {
    return "<html>\n" + "<header><title>Welcome</title></header>\n" +
            "<body>\n" + "Register new account\n" +
            "\"<br/><br/>" +
            "<form action=\"servlet/Register\" method=\"post\">\n" +
            "Name:<input type=\"text\" name=\"userName\"/><br/><br/>\n" +
            "Email Id:<input type=\"text\" name=\"userEmail\"/><br/><br/>\n" +
            "Password:<input type=\"password\" name=\"userPass\"/><br/><br/>\n" +
            "Confirm password:<input type=\"password\" name=\"userPass\"/><br/><br/>\n" +
            "<input type=\" button \" value=\" register \"/>\n" +
            "</form>"+
            "</body>\n" +
            "</html>";
  }
*/
  @GetMapping
  public String register() {
    return "register";
  }
  @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name");
        return "greeting";
    }

  @PostMapping
  @ResponseBody
  public String register(@RequestBody RegistrationRequest registrationRequest) {
    return registrationService.register(registrationRequest);
  }

  @GetMapping(path = "confirm")
    @ResponseBody
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}
