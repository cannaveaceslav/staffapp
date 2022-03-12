package com.staffapp.backend.controller.Employee;

import com.staffapp.backend.model.Employee;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.employee.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
@Api("Controller for all employees page")
public class EmployeeController {

  private final EmployeeService employeeService;


  @GetMapping(produces = "application/json")
  @ApiOperation("Method returns a response with the map with key=employees and value=list of all employees")
  public ResponseEntity<Response> getEmployees() throws InterruptedException {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("employees", employeeService.list()))
                    .message("Employees retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }
   @GetMapping("/get/{id}")
  @ApiOperation("Method gets indicated employee by id.")
  public ResponseEntity<Response> getEmployee(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("employee", employeeService.getById(id)))
                    .message("Employee with id " + id + " retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @PostMapping("/save")
  @ApiOperation("Method to add new employee." +
          "Returns a response with the map with key=employee and value=new employee")
  public ResponseEntity<Response> saveEmployee(@RequestBody @Valid Employee employee) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("employee", employeeService.create(employee)))
                    .message("New employee created")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @PutMapping("/update")
  @ApiOperation("Method update current employee." +
          "Returns a response with the map with key=employee and value=new employee")
  public ResponseEntity<Response> updateEmployee(@RequestBody @Valid Employee employee, MultipartFile file) {
      Employee emp = employee;
//      emp.setImage()
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("employee", employeeService.update(employee)))
                    .message("Employee updated")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation("Method deletes indicated employee by id.")
  public ResponseEntity<Response> deleteEmployee(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("deleted", employeeService.delete(id)))
                    .message("Employee with id " + id + " deleted")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }


}
