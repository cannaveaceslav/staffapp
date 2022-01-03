import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {tap, catchError} from "rxjs/operators";
import {CustomResponse} from "../interface/custom-response";
import {Location} from "../interface/location";

@Injectable({providedIn: 'root'})
export class LayoutService {
  private readonly apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  // getLocations(): Observable<CustomResponse>{
  //   return this.http.get<CustomResponse>('http://localhost:8080/server/list');
  // }

  getlocations$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/layout`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  save$ = (location: Location)=> <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.apiUrl}/layout/save`, location)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  location$ = (locationId: number) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/layout/get/${locationId}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  delete$ = (locationId: number)=> <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.apiUrl}/layout/delete/${locationId}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );




  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }
}
