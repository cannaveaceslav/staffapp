import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {CustomResponse} from "../interface/custom-response";
import {environment} from "../../environments/environment";
import {catchError, tap} from "rxjs/operators";
import {Item} from "../interface/item";

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private baseURL = environment.serverUrl;
  constructor(private httpClient: HttpClient) {
  }

  public getItems$ = <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/items`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public getItemsByItemTypeId$ = (itemTypeId: number) => <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/items/${itemTypeId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public item$ = (itemId: number) => <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/items/get/${itemId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public save$ = (item: Item) => <Observable<CustomResponse>>
    this.httpClient.post<CustomResponse>(`${this.baseURL}/items/save/`, item)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (item: Item) => <Observable<CustomResponse>>
    this.httpClient.delete<CustomResponse>(`${this.baseURL}/items/delete/` + item.id)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );


  private  handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }



}
