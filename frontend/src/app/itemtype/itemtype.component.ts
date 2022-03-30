import { Component, OnInit } from '@angular/core';
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {DataState} from "../enum/data-state.enum";
import {CustomResponse} from "../interface/custom-response";
import {ItemTypeService} from "../service/itemtype.service";
import {Router} from "@angular/router";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-itemtype',
  templateUrl: './itemtype.component.html',
  styleUrls: ['./itemtype.component.css']
})
export class ItemtypeComponent implements OnInit {
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  constructor(private itemTypeService: ItemTypeService, private router:Router) { }

  ngOnInit(): void {
    this.appState$ = this.itemTypeService.getItemTypes$
      .pipe(
        map(response => {
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  getItemsByItemType(itemTypeId: number)     {
    {
    let itemsPage = {
      url : `items/${itemTypeId}`
    };
    console.log(itemsPage)
    this.router.navigateByUrl(itemsPage.url);
  }
  }



}
