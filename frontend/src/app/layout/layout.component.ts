import {Component, Input, OnInit} from '@angular/core';
import {LayoutService} from "../service/layout.service";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {CustomResponse} from "../interface/custom-response";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {CdkDragStart, DragAxis} from "@angular/cdk/drag-drop";
import {Location} from '../interface/location';
import {Router} from "@angular/router";

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
  public currentUser = sessionStorage.getItem('username');


  constructor(private layoutService: LayoutService, private router: Router) {
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
  if(this.currentUser =="admin") {
    let element = event.source.getRootElement();
    let boundingClientRect = element.getBoundingClientRect();
    let parentPosition = this.getPosition(element);
    console.log("******" + 'x: ' + (boundingClientRect.x - parentPosition.left), 'y: ' + (boundingClientRect.y - parentPosition.top));
    location2.pos_x = boundingClientRect.x - parentPosition.left;
    location2.pos_y = boundingClientRect.y - parentPosition.top;
    console.log(location2.pos_y + "   " + location2.pos_x);
    this.saveLocation(location2);
  }
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
          this.ngOnInit();

          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );


  }
  public handleClickAvailable(event: MouseEvent): void {
    if (this.dragging) {
      this.dragging = false;
      return
    }
    alert('Location is free!');
  }

  public handleClick(event: MouseEvent, location: Location): void {
    if (this.dragging) {
      this.dragging = false;
      return
    }
    // alert('clicked!'+location.locationNumber);
    this.getEmployeePage(location.employee?.id)
  }

  getEmployeePage(employeeId: number) {
    {
      let employeePage = {
        url : `employee/${employeeId}`
      };
      this.router.navigateByUrl(employeePage.url);
    }
  }



}
