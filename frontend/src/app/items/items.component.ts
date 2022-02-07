import {Component, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {DataState} from "../enum/data-state.enum";
import {ActivatedRoute, Router} from "@angular/router";
import {map, Observable, of, shareReplay, startWith} from "rxjs";
import {MatPaginator} from "@angular/material/paginator";
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {Item} from "../interface/item";
import {MatSort} from "@angular/material/sort";
import {catchError} from "rxjs/operators";
import {ItemService} from "../service/item.service";
import {CustomResponse} from "../interface/custom-response";
import {AppState} from "../interface/app-state";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  items?: Item[] = []
  dataSource!: MatTableDataSource<Item[]>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;
  public value: any;
  displayedColumns: string[] = ['ID'
    , 'ITEM NAME'
    , 'TYPE'
    , 'BARCODE'
    , 'DESCRIPTION'
    , 'MANUFACTURED AT'
    , 'EMPLOYEE'
    , 'LOCATION'
    , 'IMAGE'];


  constructor(private router: Router
    , private itemService: ItemService
    , private breakpointObserver: BreakpointObserver
    , private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.value = this.route.snapshot.params['id'];
    this.appState$ = this.itemService.getItemsByItemTypeId$(this.value)
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


  getItemPage(itemId: number) {
    {
      let itemsPage = {
        url: `item/${itemId}`
      };
      console.log(itemsPage)
      this.router.navigateByUrl(itemsPage.url);
    }

  }
}
