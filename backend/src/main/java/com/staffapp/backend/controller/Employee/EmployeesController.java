package com.staffapp.backend.controller.Employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

  @GetMapping
  public String showAllEmployees(){
    return "employees";
  }
}
