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
  @Input() locationDTO!: Location;
  public dragging!: boolean;


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

  onDragEnded(event: { source: { getRootElement: () => any; }; }, location:Location) {

    let element = event.source.getRootElement();
    let boundingClientRect = element.getBoundingClientRect();
    let parentPosition = this.getPosition(element);
    console.log('x: ' + (boundingClientRect.x - parentPosition.left), 'y: ' + (boundingClientRect.y - parentPosition.top));
    this.locationDTO.pos_x = boundingClientRect.x - parentPosition.left;
    this.locationDTO.pos_y = boundingClientRect.y - parentPosition.top;
    console.log(this.locationDTO.pos_y+"   "+this.locationDTO.pos_x);
    this.saveLocation(this.locationDTO);

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
    this.appState$ = this.layoutService.save$(location)
      .pipe(
        map(response => {
          console.log('saving');
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
