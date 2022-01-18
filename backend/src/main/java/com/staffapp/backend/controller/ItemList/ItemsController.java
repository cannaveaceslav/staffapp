package com.staffapp.backend.controller.ItemList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/items")
@Api("Controller to work with list of items by chosen type on previous step")
public class ItemsController {

  @ApiOperation("Shows user list of items for selected type. Returns 'items.html' view")
  @GetMapping
  public String showListOfItemsByType() {
    return "items";
  }
}
