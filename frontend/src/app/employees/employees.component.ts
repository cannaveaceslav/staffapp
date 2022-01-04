import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../service/employees.service";
import {Employee} from "../interface/employee";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {CustomResponse} from "../interface/custom-response";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;

  constructor(private employeesService: EmployeesService, private router: Router) {
  }

  ngOnInit(): void {
    this.appState$ = this.employeesService.getEmployees$
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

  deleteEmployee(employee: Employee): void {
    this.appState$ = this.employeesService.delete$(employee)
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

  public goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }


  // handleSuccessfulResponse(response: any) {
  //   this.employees = response.data.employees;
  // }

}
