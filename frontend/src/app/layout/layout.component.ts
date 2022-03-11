import {Component, Input, OnInit} from '@angular/core';
import {LayoutService} from "../service/layout.service";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {CustomResponse} from "../interface/custom-response";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {CdkDragStart, DragAxis} from "@angular/cdk/drag-drop";
import {Location} from '../interface/location';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  appState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  public location!: Location;
  public dragging!: boolean;
  public showHover!: boolean;


  constructor(private layoutService: LayoutService) {
    this.showHover = false
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

  onDragEnded(event: { source: { getRootElement: () => any; }; }, location2: Location) {

    let element = event.source.getRootElement();
    let boundingClientRect = element.getBoundingClientRect();
    let parentPosition = this.getPosition(element);
    console.log("******" + 'x: ' + (boundingClientRect.x - parentPosition.left), 'y: ' + (boundingClientRect.y - parentPosition.top));
    console.log(location2)
    location2.pos_x = boundingClientRect.x - parentPosition.left;
    location2.pos_y = boundingClientRect.y - parentPosition.top;
    console.log(location2.pos_y + "   " + location2.pos_x + 'canna');
    this.ngOnInit();

    this.saveLocation(location2);
  }

  getPosition(el: any) {
    let x = 0;
    let y = 0;
    while (el && !isNaN(el.offsetLeft) && !isNaN(el.offsetTop)) {
      x += el.offsetLeft - el.scrollLeft;
      y += el.offsetTop - el.scrollTop;
      el = el.offsetParent;
    }
    return {top: y, left: x};
  }

  public handleDragStart(event: CdkDragStart): void {
    this.dragging = true;
  }

  saveLocation(location: Location): void {
    this.appState$ = this.layoutService.update$(location)
      .pipe(
        map(response => {
          console.log('updating');
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );

  }

  public handleClick(event: MouseEvent): void {
    if (this.dragging) {
      this.dragging = false;
      return
    }
    alert('clicked!');
  }



}
