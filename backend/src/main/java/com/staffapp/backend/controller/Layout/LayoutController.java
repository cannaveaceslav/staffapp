package com.staffapp.backend.controller.Layout;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/layout")
@Api("Controller to work with layout of the office. Add and remove working tables (locations)")
public class LayoutController {

  @GetMapping
  @ApiOperation("Shows layout with all locations in the office. Returns 'layout.html' view")
  public String showLayout() {
    return "layout";
  }

  @PostMapping
  @ApiOperation("Add new location to layout. Returns 'layout.html' view")
  public String addLocation() {
    return "layout";
  }

  @DeleteMapping
  @ApiOperation("Remove selected location from layout. Returns 'layout.html' view")
  public String deleteLocation() {
    return "layout";
  }

}
