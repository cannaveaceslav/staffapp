package com.staffapp.backend.controller.Department;

import com.staffapp.backend.model.Department;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.department.DepartmentService;
import com.staffapp.backend.service.layout.LayoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/departments")
@Api("Controller to work with layout of the office. Add and remove working tables (locations)")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @ApiOperation("Method returns a response with the map with key=locations and value=list of all locations")
    public ResponseEntity<Response> getDepartments() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("departments", departmentService.list()))
                        .message("Departments retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    @ApiOperation("Method returns a response with the map with key=location and value=location by id")
    public ResponseEntity<Response> getLocation(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("department", departmentService.getById(id)))
                        .message("Department with id " + id + " retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    @ApiOperation("Method to add new department." +
            "Returns a response with the map with key=location and value=new location")
    public ResponseEntity<Response> saveLocation(@RequestBody @Valid Department department) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("department", departmentService.create(department)))
                        .message("department created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping("/update")
    @ApiOperation("Method update current department." +
            "Returns a response with the map with key=location and value=new location")
    public ResponseEntity<Response> updateLocation(@RequestBody @Valid Department department) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("department", departmentService.update(department)))
                        .message("Department updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Method deletes indicated location by id.")
    public ResponseEntity<Response> deleteDepartment(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("deleted", departmentService.delete(id)))
                        .message("Department with id " + id + " deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
