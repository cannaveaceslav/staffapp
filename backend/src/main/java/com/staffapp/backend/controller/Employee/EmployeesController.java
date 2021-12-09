package com.staffapp.backend.controller.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
@Api("Controller for all employees page")
public class EmployeesController {

  @ApiOperation("Show all employees from employee table. Return view 'employees.html'")
  @GetMapping
  public String showAllEmployees(){
    return "employees";
  }
}
