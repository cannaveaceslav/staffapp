import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {ActivatedRoute, Router} from "@angular/router";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {DataState} from "../../enum/data-state.enum";
import {CustomResponse} from "../../interface/custom-response";
import {AppState} from "../../interface/app-state";
import {Employee} from "../../interface/employee";
import {EmployeesService} from "../../service/employees.service";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-employees-admin',
  templateUrl: './employees-admin.component.html',
  styleUrls: ['./employees-admin.component.css']
})
export class EmployeesAdminComponent implements OnInit {
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  addNewEmployee: boolean = false;
  employees?: Employee[] = [];
  first = 0;
  rows = 15;

  constructor(private employeesService: EmployeesService
    , private router: Router
    , private breakpointObserver: BreakpointObserver
    , public dialog: MatDialog
    , private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.appState$ = this.employeesService.getEmployees$
      .pipe(
        map(response => {
          this.employees = response.data.employees;
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.employees ? this.first === (this.employees.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.employees ? this.first === 0 : true;
  }

  enableAddProductComp() {
    this.addNewEmployee = !this.addNewEmployee;
  }

  closeComp() {
    this.addNewEmployee = false;
  }

  loadEmployees() {
    this.appState$ = this.employeesService.getEmployees$
      .pipe(
        map(response => {
          this.employees = response.data.employees;
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }


}
