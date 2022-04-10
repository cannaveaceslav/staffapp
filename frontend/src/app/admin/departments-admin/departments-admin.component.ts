import {Component, OnInit, ViewChild} from '@angular/core';
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {AppState} from "../../interface/app-state";
import {CustomResponse} from "../../interface/custom-response";
import {Location} from "../../interface/location";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {LayoutService} from "../../service/layout.service";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError} from "rxjs/operators";
import {EditLocationComponent} from "../locations-admin/edit-location/edit-location.component";
import {AddLocationComponent} from "../locations-admin/add-lcoation/add-location.component";
import { DataState } from 'src/app/enum/data-state.enum';
import {Department} from "../../interface/department";
import {DepartmentService} from "../../service/department.service";
import {EditDepartmentComponent} from "./edit-department/edit-department.component";
import {AddDepartmentComponent} from "./add-department/add-department.component";

@Component({
  selector: 'app-departments-admin',
  templateUrl: './departments-admin.component.html',
  styleUrls: ['./departments-admin.component.css']
})
export class DepartmentsAdminComponent implements OnInit {

  displayedColumns: string[] = ['ID', 'DEPARTMENT NAME', 'DESCRIPTION', 'ENABLED', 'CREATED AT', 'EDIT', 'DELETE'];
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  departments?: Department[] = []
  newDepartment?: Location;
  dataSource!: MatTableDataSource<Department[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private dialog: MatDialog
    , private departmentService: DepartmentService
    , private breakpointObserver: BreakpointObserver
    , private snackBar: MatSnackBar) {
    this.departmentService.listen().subscribe((m: any) => {
      console.log(m);
      this.refreshTable();
    })
  }

  ngOnInit(): void {
    this.appState$ = this.departmentService.getDepartments$
      .pipe(
        map(response => {
          this.departments = response.data.departments;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.departments);
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
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  delete(department: Department): void {
    if (confirm('Are you sure to delete?')) {
      this.appState$ = this.departmentService.delete$(department.id)
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

  edit(department: Department) {
    this.departmentService.formData = department;
    console.log("Edit department clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(EditDepartmentComponent, dialogConfig);

  }


  addLocation() {
    console.log("Add new department clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(AddDepartmentComponent, dialogConfig);
  }

  private refreshTable() {
    this.appState$ = this.departmentService.getDepartments$
      .pipe(
        map(response => {
          this.departments = response.data.departments;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.departments);
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }


}
