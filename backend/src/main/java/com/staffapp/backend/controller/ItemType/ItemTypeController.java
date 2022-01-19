package com.staffapp.backend.controller.ItemType;

import com.staffapp.backend.model.ItemType;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.itemType.ItemTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/item-types")
@Api("Controller for page with item types (chairs, laptops, keyboards etc.")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemTypeController {

  private final ItemTypeService itemTypeService;

  @GetMapping
  @ApiOperation("Method returns a response with the map with key=itemTypes and value=list of all itemTypes")
  public ResponseEntity<Response> getItemTypes() {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("itemTypes", itemTypeService.list()))
                    .message("List of itemTypes retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @GetMapping("/get/{id}")
  @ApiOperation("Method returns a response with the map with key=itemType and value=itemType by id")
  public ResponseEntity<Response> getItemType(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("itemType", itemTypeService.getById(id)))
                    .message("ItemType with id " + id + " retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @PostMapping("/save")
  @ApiOperation("Method to add new itemType." +
          "Returns a response with the map with key=itemType and value=new itemType")
  public ResponseEntity<Response> saveItemType(@RequestBody @Valid ItemType itemType) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("itemType", itemTypeService.create(itemType)))
                    .message("ItemType created")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @PutMapping("/update")
  @ApiOperation("Method update current itemType." +
          "Returns a response with the map with key=itemType and value=itemType")
  public ResponseEntity<Response> updateItemType(@RequestBody @Valid ItemType itemType) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("itemType", itemTypeService.update(itemType)))
                    .message("ItemType updated")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation("Method deletes indicated itemType by id.")
  public ResponseEntity<Response> deleteLocation(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("itemType", itemTypeService.delete(id)))
                    .message("ItemType with id " + id + " deleted")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }
}
