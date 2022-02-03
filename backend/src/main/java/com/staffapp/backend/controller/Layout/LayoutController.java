package com.staffapp.backend.controller.Layout;

import com.staffapp.backend.model.Location;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.layout.LayoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/layout")
@Api("Controller to work with layout of the office. Add and remove working tables (locations)")
public class LayoutController {

  private final LayoutService layoutService;

  @GetMapping
  @ApiOperation("Method returns a response with the map with key=locations and value=list of all locations")
  public ResponseEntity<Response> getLocations() {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("locations", layoutService.list()))
                    .message("Locations retrie  ved")
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
                    .data(Collections.singletonMap("location", layoutService.getById(id)))
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


}
