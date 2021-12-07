package com.staffapp.backend.controller.Reports;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportsController {

  @GetMapping
  public String showReports(){
    return "reports";
  }
}
