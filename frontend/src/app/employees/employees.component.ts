import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../service/employees.service";
import {Employee} from "../interface/employee";
import {Company} from "../interface/company";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  employees!: Employee[];

  constructor(private employeesService: EmployeesService) {
  }

  ngOnInit() {
    this.employeesService.getEmployees().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => console.log(error)
    );

  }

  handleSuccessfulResponse(response: any) {
    this.employees = response.data.employees;
  }

}
