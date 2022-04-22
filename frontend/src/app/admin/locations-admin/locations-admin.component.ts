import {Component, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {DataState} from "../../enum/data-state.enum";
import {MatPaginator} from "@angular/material/paginator";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {catchError} from "rxjs/operators";
import {CustomResponse} from "../../interface/custom-response";
import {AppState} from "../../interface/app-state";
import {Location} from "../../interface/location";
import {LayoutService} from "../../service/layout.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EditLocationComponent} from "./edit-location/edit-location.component";
import {AddLocationComponent} from "./add-lcoation/add-location.component";

@Component({
  selector: 'app-locations-admin',
  templateUrl: './locations-admin.component.html',
  styleUrls: ['./locations-admin.component.css']
})
export class LocationsAdminComponent implements OnInit {

  displayedColumns: string[] = ['LOCATION NUMBER', 'DESCRIPTION', 'TYPE', 'EMPLOYEE', 'AVAILABLE', 'POS X', 'POS Y', 'EDIT', 'DELETE'];
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  locations?: Location[] = []
  newLocation?: Location;
  dataSource!: MatTableDataSource<Location[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private dialog: MatDialog
    , private locationService: LayoutService
    , private breakpointObserver: BreakpointObserver
    , private snackBar: MatSnackBar) {
    this.locationService.listen().subscribe((m: any) => {
      console.log(m);
      this.refreshTable();
    })
  }

  ngOnInit(): void {
    this.appState$ = this.locationService.getlocations$
      .pipe(
        map(response => {
          this.locations = response.data.locations;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.locations);
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

  delete(location: Location): void {
    if (confirm('Are you sure to delete?')) {
      this.appState$ = this.locationService.delete$(location.id)
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

  edit(location: Location) {
    this.locationService.formData = location;
    console.log("Edit location clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(EditLocationComponent, dialogConfig);

  }


  addLocation() {
    console.log("Add new location clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(AddLocationComponent, dialogConfig);
  }

  private refreshTable() {
    this.appState$ = this.locationService.getlocations$
      .pipe(
        map(response => {
          this.locations = response.data.locations;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.locations);
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }


}
