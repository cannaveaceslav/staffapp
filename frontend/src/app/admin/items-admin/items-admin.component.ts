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
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";


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
  dataSource!: MatTableDataSource<Item[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private itemService: ItemService
    , private breakpointObserver: BreakpointObserver) {
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
    this.appState$ = this.itemService.delete$(item)
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

  edit(item: Item) {

  }

  addItem(newItem: any) {

  }
}
