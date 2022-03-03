package com.staffapp.backend.controller.Item;

import com.staffapp.backend.model.Item;
import com.staffapp.backend.model.Response;
import com.staffapp.backend.service.item.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/items")
@Api("Controller to work with list of items by chosen type on previous step")
@CrossOrigin(origins = "http://localhost:4200")

public class ItemController {

  private final ItemService itemService;

  @GetMapping
  @ApiOperation("Method returns a response with the map with key=items and value=list of all items")
  public ResponseEntity<Response> getItems() {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("items", itemService.list()))
                    .message("List of items retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @GetMapping("/{id}")
  @ApiOperation("Method returns a response with the map with key=items and value=list of all items by itemTypeId")
  public ResponseEntity<Response> getItemsByItemTypeId(@PathVariable("id") Long id) {
    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("items", itemService.listByItemTypeId(id)))
                    .message("List of items by itemType retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @GetMapping("/get/{id}")
  @ApiOperation("Method returns a response with the map with key=item and value=item by id")
  public ResponseEntity<Response> getItem(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("item", itemService.getById(id)))
                    .message("Item with id " + id + " retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @PostMapping("/save")
  @ApiOperation("Method to add new item." +
          "Returns a response with the map with key=item and value=new item")
  public ResponseEntity<Response> saveItem(@RequestBody @Valid Item item) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("item", itemService.create(item)))
                    .message("Item created")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }

  @PutMapping("/update")
  @ApiOperation("Method update current itemType." +
          "Returns a response with the map with key=item and value=item")
  public ResponseEntity<Response> updateItem(@RequestBody @Valid Item item) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("item", itemService.update(item)))
                    .message("Item updated")
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .build()
    );
  }


  @DeleteMapping("/delete/{id}")
  @ApiOperation("Method deletes indicated item by id.")
  public ResponseEntity<Response> deleteItem(@PathVariable("id") Long id) {

    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("item", itemService.delete(id)))
                    .message("item with id " + id + " deleted")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

  @PostMapping(path="/link", consumes = "application/x-www-form-urlencoded;charset=UTF-8",produces = "application/json")
  @ApiOperation("Method that updates selected item with employee.")
  public ResponseEntity<Response> linkItem(@RequestParam("employeeId") Long employeeId,
                                             @RequestParam("itemId") Long itemId) {


    return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .data(Collections.singletonMap("ISUPDATED", itemService.updateByEmployeeId(employeeId, itemId) ))
                    .message("Employee [" + employeeId + "] linked to item [" + itemId+"]")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
    );
  }

}
