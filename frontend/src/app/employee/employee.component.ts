import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EmployeesService} from "../service/employees.service";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {CustomResponse} from "../interface/custom-response";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {Employee} from "../interface/employee";


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  public value: any;
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  public employee?: Employee;

  constructor(private route: ActivatedRoute, private employeesService: EmployeesService) {
  }

  ngOnInit(): void {
    this.value = this.route.snapshot.params['id'];
    this.appState$ = this.employeesService.getEmployee$(this.value)
      .pipe(
        map(response => {
          console.log('SUCCESS');
          this.employee =response.data.employee;
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          console.log('ERROR')
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );

    console.log(this.route.snapshot.params['id']);
  }

}
