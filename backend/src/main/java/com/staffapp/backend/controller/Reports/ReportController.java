package com.staffapp.backend.controller.Reports;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/reports")
@Api("Controller for reports page")
public class ReportController {

  @GetMapping
  @ApiOperation("Show all available reports")
  public String showReports(){
    return "reports";
  }
}
