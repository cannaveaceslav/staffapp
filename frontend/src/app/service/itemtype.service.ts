import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {CustomResponse} from "../interface/custom-response";
import {catchError, tap} from "rxjs/operators";
import {ItemType} from "../interface/itemType";

@Injectable({
  providedIn: 'root'
})
export class ItemtypeService {

    private baseURL = environment.serverUrl;
  constructor(private httpClient: HttpClient) {
  }

  public getItemTypes$ = <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/item-types`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public item$ = (itemType: number) => <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/item-types/get/${itemType}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public save$ = (itemType: ItemType) => <Observable<CustomResponse>>
    this.httpClient.post<CustomResponse>(`${this.baseURL}/item-types/save`, itemType)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (itemType: ItemType) => <Observable<CustomResponse>>
    this.httpClient.delete<CustomResponse>(`${this.baseURL}/item-types/delete/` + itemType.id)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );


  private  handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }

}
