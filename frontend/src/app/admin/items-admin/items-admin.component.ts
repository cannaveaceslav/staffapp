import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {DataState} from "../../enum/data-state.enum";
import {CustomResponse} from "../../interface/custom-response";
import {AppState} from "../../interface/app-state";
import {catchError} from "rxjs/operators";
import {Item} from "../../interface/item";
import {ItemService} from "../../service/item.service";
import {MatSort, Sort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EditItemComponent} from "./edit-item/edit-item.component";
import {AddItemComponent} from "./add-item/add-item.component";
import {LiveAnnouncer} from "@angular/cdk/a11y";


@Component({
  selector: 'app-items-admin',
  templateUrl: './items-admin.component.html',
  styleUrls: ['./items-admin.component.css']
})
export class ItemsAdminComponent implements OnInit {
  displayedColumns: string[] = ['ID'
    , 'ITEM NAME'
    , 'TYPE'
    , 'BARCODE'
    , 'DESCRIPTION'
    , 'MANUFACTURED AT'
    , 'EMPLOYEE'
    , 'LOCATION'
    , 'IMAGE'
    , 'EDIT'
    , 'DELETE'];
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  items?: Item[] = []
  newItem?: Item;
  sort!: Sort
  dataSource!: MatTableDataSource<Item[]>;

  @ViewChild(MatPaginator) set matPaginator(paginator: MatPaginator) {
    if (!this.dataSource.paginator) {
      this.dataSource.paginator = paginator;
    }
  }

  @ViewChild(MatSort) set matSort(sort: MatSort) {
    if (!this.dataSource.sort) {
      this.dataSource.sort = sort;
    }
  }

  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private dialog: MatDialog
    , private itemService: ItemService
    , private _liveAnnouncer: LiveAnnouncer
    , private breakpointObserver: BreakpointObserver
    , private snackBar: MatSnackBar) {
    this.itemService.listen().subscribe((m: any) => {
      console.log(m);
      this.refreshTable();
    })
  }

  ngOnInit(): void {
    this.appState$ = this.itemService.getItems$
      .pipe(
        map(response => {
          this.items = response.data.items;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.items);
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

  delete(item: Item): void {
    if (confirm('Are you sure to delete?')) {
      this.appState$ = this.itemService.delete$(item)
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

  edit(item: Item) {
    this.itemService.formData = item;
    console.log("Edit item clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(EditItemComponent, dialogConfig);
  }

  addItem() {
    console.log("Add new Item clicked")
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(AddItemComponent, dialogConfig);

  }

  private refreshTable() {
    this.appState$ = this.itemService.getItems$
      .pipe(
        map(response => {
          this.items = response.data.items;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.items);
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

}
