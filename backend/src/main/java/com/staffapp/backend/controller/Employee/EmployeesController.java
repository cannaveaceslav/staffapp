package com.staffapp.backend.controller.Employee;

import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.employee.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
//@CrossOrigin(origins = "http://localhost:4200")
@Api("Controller for all employees page")
public class EmployeesController {

  private final EmployeeService employeeService;


//  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping
  @ApiOperation("Method returns a response with the map with key=employees and value=list of all employees")
  public ResponseEntity<Response> getLocations() {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("employees", employeeService.list(100)))
                    .message("Employees retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }
}
