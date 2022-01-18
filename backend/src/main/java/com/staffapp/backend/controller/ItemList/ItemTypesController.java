package com.staffapp.backend.controller.ItemList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/item-types")
@Api("Controller for page with item types (chairs, laptops, keyboards etc.")
public class ItemTypesController {

  @GetMapping
  @ApiOperation("Shows user list of available type of items in the office. Returns 'item-type.html' view")
  public String showListOfItems() {
    return "item-types";
  }
}
