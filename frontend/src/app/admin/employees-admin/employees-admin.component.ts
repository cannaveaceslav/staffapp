import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {ActivatedRoute, Router} from "@angular/router";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {AddEmployeeComponent} from "../../admin/employees-admin/add-employee/add-employee.component";
import {DataState} from "../../enum/data-state.enum";
import {CustomResponse} from "../../interface/custom-response";
import {AppState} from "../../interface/app-state";
import {Employee} from "../../interface/employee";
import {EmployeesService} from "../../service/employees.service";
import {catchError} from "rxjs/operators";
import {MatSort} from "@angular/material/sort";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialogConfig, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ItemType} from "../../interface/itemType";
import {EditTypeComponent} from "../item-types-admin/edit-type/edit-type.component";
import {EditEmployeeComponent} from "./edit-employee/edit-employee.component";

@Component({
  selector: 'app-employees-admin',
  templateUrl: './employees-admin.component.html',
  styleUrls: ['./employees-admin.component.css']
})
export class EmployeesAdminComponent implements OnInit {
  displayedColumns: string[] = ['ID'
    , 'LAST NAME'
    , 'FIRST NAME'
    , 'COMPANY'
    , 'DEPARTMENT'
    , 'EMAIL'
    , 'BIRTHDAY'
    , 'LOCATION'
    , 'IMAGE'
    , 'EDIT'
    , 'DELETE'];
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  employees?: Employee[] = []
  newEmployee?: Employee;
  dataSource!: MatTableDataSource<Employee[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private dialog: MatDialog
    , private employeeService: EmployeesService
    , private breakpointObserver: BreakpointObserver
    , private snackBar: MatSnackBar) {
    this.employeeService.listen().subscribe((m: any) => {
      console.log(m);
      this.refreshTable();
    })
  }

  ngOnInit(): void {
    this.appState$ = this.employeeService.getEmployees$
      .pipe(
        map(response => {
          this.employees = response.data.employees;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.employees);
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


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLocaleLowerCase();
  }

  delete(employee: Employee): void {
    if (confirm('Are you sure to delete?')) {
      this.appState$ = this.employeeService.delete$(employee)
        .pipe(
          map(response => {
            this.snackBar.open(response.message, '', {
              duration: 4000,
              verticalPosition: 'top'
            });
            this.ngOnInit();
            return {dataState: DataState.LOADED_STATE, appData: response}
          }),
          startWith({dataState: DataState.LOADING_STATE}),
          catchError((error: string) => {
            return of({dataState: DataState.ERROR_STATE, error: error})
          })
        );
    }
  }

  edit(employee: Employee) {
    this.employeeService.formData = employee;
    console.log("Edit employee clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(EditEmployeeComponent, dialogConfig);

  }

  addEmployee() {
    console.log("Add new employee clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(AddEmployeeComponent, dialogConfig);

  }

  private refreshTable() {
    this.appState$ = this.employeeService.getEmployees$
      .pipe(
        map(response => {
          this.employees = response.data.employees;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.employees);
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }
}
