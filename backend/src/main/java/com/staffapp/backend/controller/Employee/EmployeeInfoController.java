package com.staffapp.backend.controller.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/employee-info")
@Api("Controller for employee info page. Show detailed info for each chosen employee")
public class EmployeeInfoController {

  @ApiOperation("Show user page for chosen employee with all details. Return view 'employee-info.html'")
  @GetMapping
  public String showEmployeeInfo(){
    return "employee-info";
  }
}
