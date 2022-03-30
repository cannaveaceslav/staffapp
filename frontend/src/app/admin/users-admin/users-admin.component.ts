import {Component, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {User} from "../../interface/user";
import {MatSort} from "@angular/material/sort";
import {catchError} from "rxjs/operators";
import {DataState} from "../../enum/data-state.enum";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {CustomResponse} from "../../interface/custom-response";
import {AppState} from "../../interface/app-state";
import {MatPaginator} from "@angular/material/paginator";
import {UserService} from "../../service/user.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EditUserComponent} from "./edit-user/edit-user.component";

@Component({
  selector: 'app-users-admin',
  templateUrl: './users-admin.component.html',
  styleUrls: ['./users-admin.component.css']
})
export class UsersAdminComponent implements OnInit {

  displayedColumns: string[] = ['ID'
    , 'LAST NAME'
    , 'FIRST NAME'
    , 'EMAIL'
    , 'PASSWORD'
    , 'USER ROLE'
    , 'LOCKED'
    , 'ENABLED'
    , 'EDIT'
    , 'DELETE'];

  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  users?: User[] = []
  dataSource!: MatTableDataSource<User[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private dialog: MatDialog
    , private userService: UserService
    , private breakpointObserver: BreakpointObserver
    , private snackBar: MatSnackBar) {
    this.userService.listen().subscribe((m: any) => {
      console.log(m);
      this.refreshTable();
    })
  }

  ngOnInit(): void {
    this.appState$ = this.userService.getUsers$
      .pipe(
        map(response => {
          this.users = response.data.users;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.users);
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

  delete(user: User): void {
    if (confirm('Are you sure to delete?')) {
      this.appState$ = this.userService.delete$(user.id)
        .pipe(
          map(response => {
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

  edit(user: User) {
    this.userService.formData = user;
    console.log("Edit user clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(EditUserComponent, dialogConfig);

  }

  private refreshTable() {
    this.appState$ = this.userService.getUsers$
      .pipe(
        map(response => {
          this.users = response.data.users;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.users);
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

}
