import {Component, OnInit} from '@angular/core';
import {LayoutService} from "../service/layout.service";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {CustomResponse} from "../interface/custom-response";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;

  constructor(private layoutService: LayoutService) {
  }

  ngOnInit(): void {
    this.appState$ = this.layoutService.getlocations$
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
