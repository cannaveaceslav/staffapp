package com.staffapp.backend.controller.User;

import com.staffapp.backend.model.Location;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.model.User;
import com.staffapp.backend.service.layout.LayoutService;
import com.staffapp.backend.service.user.UserService;
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
@RequestMapping(path = "/users")
@Api("Controller to work with application`s users")
public class UserController {


  private final UserService userService;

  @GetMapping
  @ApiOperation("Method returns a response with the map with key=users and value=list of all users")
  public ResponseEntity<Response> getUsers() {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("users", userService.list()))
                    .message("Users retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @GetMapping("/get/{id}")
  @ApiOperation("Method returns a response with the map with key=user and value=location by id")
  public ResponseEntity<Response> getLocation(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("user", userService.getById(id)))
                    .message("User with id " + id + " retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @PostMapping("/save")
  @ApiOperation("Method to add new user." +
          "Returns a response with the map with key=user and value=new user")
  public ResponseEntity<Response> saveLocation(@RequestBody @Valid User user) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("users", userService.create(user)))
                    .message("User created")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @PutMapping("/update")
  @ApiOperation("Method update current location." +
          "Returns a response with the map with key=location and value=new location")
  public ResponseEntity<Response> updateLocation(@RequestBody @Valid User user) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("user", userService.update(user)))
                    .message("User updated")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation("Method deletes indicated user by id.")
  public ResponseEntity<Response> deleteLocation(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("deleted", userService.deleteById(id)))
                    .message("Users with id " + id + " deleted")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }


}
