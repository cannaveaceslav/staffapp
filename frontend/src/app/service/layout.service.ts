import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, Subject, throwError} from "rxjs";
import {tap, catchError} from "rxjs/operators";
import {CustomResponse} from "../interface/custom-response";
import {Location} from "../interface/location";
import {environment} from "../../environments/environment";
import {ItemType} from "../interface/itemType";



@Injectable({providedIn: 'root'})
export class LayoutService {
  private readonly baseURL = environment.serverUrl;
  formData?: Location;

  constructor(private http: HttpClient) {
  }

  getlocations$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.baseURL}/layout`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  save$ = (location: Location)=> <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.baseURL}/layout/save`, location)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  location$ = (locationId: number) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.baseURL}/layout/get/${locationId}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  delete$ = (locationId: number)=> <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.baseURL}/layout/delete/${locationId}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );



  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }


  private _listeners = new Subject<any>();
  listen(): Observable<any>{
    return this._listeners.asObservable();
  }

  filter(filterBy: string){
    this._listeners.next(filterBy);
  }
  public update$ = (location: Location) => <Observable<CustomResponse>>
    this.http.put<CustomResponse>(`${this.baseURL}/layout/update`, location)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

}
