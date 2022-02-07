import {Component, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {DataState} from "../../enum/data-state.enum";
import {ItemType} from "../../interface/itemType";
import {ItemtypeService} from "../../service/itemtype.service";
import {MatPaginator} from "@angular/material/paginator";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {Item} from "../../interface/item";
import {MatSort} from "@angular/material/sort";
import {catchError} from "rxjs/operators";
import {CustomResponse} from "../../interface/custom-response";
import {AppState} from "../../interface/app-state";

@Component({
  selector: 'app-item-types-admin',
  templateUrl: './item-types-admin.component.html',
  styleUrls: ['./item-types-admin.component.css']
})
export class ItemTypesAdminComponent implements OnInit {

 displayedColumns: string[] = ['ID', 'ITEM TYPE', 'DESCRIPTION', 'IMAGE','EDIT', 'DELETE'];
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  itemTypes?: ItemType[] = []
  newItemType?: ItemType;
  dataSource!: MatTableDataSource<ItemType[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;


  constructor(private router: Router
    , private itemTypeService: ItemtypeService
    , private breakpointObserver: BreakpointObserver) {
  }

  ngOnInit(): void {
    this.appState$ = this.itemTypeService.getItemTypes$
      .pipe(
        map(response => {
          this.itemTypes = response.data.itemTypes;
          // @ts-ignore
          this.dataSource = new MatTableDataSource(this.itemTypes);
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

  delete(itemType: ItemType): void {
    this.appState$ = this.itemTypeService.delete$(itemType)
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
