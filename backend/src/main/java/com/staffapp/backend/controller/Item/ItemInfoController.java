package com.staffapp.backend.controller.Item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/item-info")
public class ItemInfoController {

  @GetMapping
  public String showItemInfo(){
    return "item-info";
  }
}
