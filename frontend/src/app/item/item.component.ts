import { Component, OnInit } from '@angular/core';
import {ItemService} from "../service/item.service";
import {map, Observable, of, startWith} from "rxjs";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {
  public appState$!: Observable<unknown>;

  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
      this.appState$ = this.itemService.getItems$
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

}
