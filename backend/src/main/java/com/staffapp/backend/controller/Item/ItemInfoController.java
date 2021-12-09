package com.staffapp.backend.controller.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/item-info")
@Api("Controller to work with each item")
public class ItemInfoController {

  @GetMapping
  @ApiOperation("Shows user detailed info for selected item. Returns 'item-info.html' view")
  public String showItemInfo(){
    return "item-info";
  }
}
