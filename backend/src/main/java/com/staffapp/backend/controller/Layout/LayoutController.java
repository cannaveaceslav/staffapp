package com.staffapp.backend.controller.Layout;

import com.staffapp.backend.model.Location;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.layout.LayoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/layout")
@Api("Controller to work with layout of the office. Add and remove working tables (locations)")
public class LayoutController {

    private final LayoutService layoutService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    @ApiOperation("Method returns a response with the map with key=locations and value=list of all locations")
    public ResponseEntity<Response> getLocations() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("locations", layoutService.list()))
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
                        .data(Collections.singletonMap("location", layoutService.get(id)))
                        .message("Location with id " + id + " retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    @ApiOperation("Method to add new location." +
            "Returns a response with the map with key=location and value=new location")
    public ResponseEntity<Response> saveLocation(@RequestBody @Valid Location location) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("location", layoutService.create(location)))
                        .message("Location created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping("/update")
    @ApiOperation("Method update current location." +
            "Returns a response with the map with key=location and value=new location")
    public ResponseEntity<Response> updateLocation(@RequestBody @Valid Location location) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("location", layoutService.update(location)))
                        .message("Location updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Method deletes indicated location by id.")
    public ResponseEntity<Response> deleteLocation(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Collections.singletonMap("deleted", layoutService.delete(id)))
                        .message("Location with id " + id + " deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

//    @GetMapping
//    @ApiOperation("Shows layout with all locations in the office. Returns 'layout.html' view")
//    public String showLayout() {
//        return "layout";
//    }
//
//    @PostMapping
//    @ApiOperation("Add new location to layout. Returns 'layout.html' view")
//    public String addLocation() {
//        return "layout";
//    }
//
//    @DeleteMapping
//    @ApiOperation("Remove selected location from layout. Returns 'layout.html' view")
//    public String deleteLocation() {
//        return "layout";
//    }

}
