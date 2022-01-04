import {Component, OnInit} from '@angular/core';
import {map, Observable, of, startWith} from "rxjs";
import {DataState} from '../../enum/data-state.enum';
import {AppState} from "../../interface/app-state";
import {CustomResponse} from "../../interface/custom-response";
import {EmployeesService} from "../../service/employees.service";
import {Employee} from "../../interface/employee";
import {catchError} from "rxjs/operators";
import {EmployeesComponent} from "../employees.component";

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  employee!: Employee;

  constructor(private employeesService: EmployeesService) {
  }

  ngOnInit(): void {
  }

  createEmployee(employee: Employee): void {
    this.appState$ = this.employeesService.save$(employee)
      .pipe(
        map(response => {
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

}
