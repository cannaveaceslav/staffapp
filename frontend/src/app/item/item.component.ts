import {Component, OnInit} from '@angular/core';
import {ItemService} from "../service/item.service";
import {map, Observable, of, startWith} from "rxjs";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {Item} from "../interface/item";
import {ActivatedRoute} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {AppState} from "../interface/app-state";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {
  public value: any;
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  public item?: Item;

  constructor(private route: ActivatedRoute, private itemService: ItemService) {
  }

  ngOnInit(): void {
    this.value = this.route.snapshot.params['id'];
    this.appState$ = this.itemService.item$(this.value)
      .pipe(
        map(response => {
          console.log('SUCCESS');
          this.item = response.data.item;
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          console.log('ERROR')
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );

    console.log(this.route.snapshot.params['id']);
  }

}
