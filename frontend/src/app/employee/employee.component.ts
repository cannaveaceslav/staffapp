import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeesService} from "../service/employees.service";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../interface/app-state";
import {CustomResponse} from "../interface/custom-response";
import {DataState} from "../enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {Employee} from "../interface/employee";
import {ItemService} from "../service/item.service";
import {Item} from "../interface/item";




@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  public value: any;
  appState$!: Observable<AppState<CustomResponse>>;
  appState2$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  public employee?: Employee;
  public items?: Item[];

  constructor(private route: ActivatedRoute,
              private router:Router,
              private employeesService: EmployeesService,
              private itemService: ItemService
  ) {
  }

  ngOnInit(): void {
    this.value = this.route.snapshot.params['id'];
    this.appState$ = this.employeesService.getEmployee$(this.value)
      .pipe(
        map(response => {
          console.log('SUCCESS getEmployee$');
          this.employee = response.data.employee;
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          console.log('ERROR getEmployee$')
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );

    this.appState2$ = this.itemService.getItemsByEmployee$(this.value)
      .pipe(
        map(response => {
          console.log('SUCCESS getItemsByEmployee$');
          this.items = response.data.items;
          return {dataState: DataState.LOADED_STATE, appData: response}
        }),
        startWith({dataState: DataState.LOADING_STATE}),
        catchError((error: string) => {
          console.log('ERROR getItemsByEmployee$' )
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );

    console.log(this.route.snapshot.params['id']);
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
