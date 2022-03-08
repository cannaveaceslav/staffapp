package com.staffapp.backend.controller.Company;

import com.staffapp.backend.model.Company;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.company.CompanyService;
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
@RequestMapping(path = "/companies")
@Api("Controller to work with layout of the office. Add and remove working tables (locations)")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @ApiOperation("Method returns a response with the map with key=locations and value=list of all locations")
    public ResponseEntity<Response> getCompanies() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("companies", companyService.list()))
                        .message("Locations retrieved")
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
                        .data(Collections.singletonMap("company", companyService.getById(id)))
                        .message("Location with id " + id + " retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    @ApiOperation("Method to add new company." +
            "Returns a response with the map with key=company and value=new Company")
    public ResponseEntity<Response> saveLocation(@RequestBody @Valid Company company) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("company", companyService.create(company)))
                        .message("company created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping("/update")
    @ApiOperation("Method update current company." +
            "Returns a response with the map with key=company and value=new company")
    public ResponseEntity<Response> updateLocation(@RequestBody @Valid Company company) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("company", companyService.update(company)))
                        .message("company updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Method deletes indicated company by id.")
    public ResponseEntity<Response> deleteCompany(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("deleted", companyService.delete(id)))
                        .message("company with id " + id + " deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
