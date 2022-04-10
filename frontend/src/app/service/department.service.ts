import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {Location} from "../interface/location";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {CustomResponse} from "../interface/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Department} from "../interface/department";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private readonly baseURL = environment.serverUrl;
  formData?: Department;

  constructor(private http: HttpClient) {
  }

  getDepartments$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.baseURL}/departments`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  save$ = (department: Department)=> <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.baseURL}/departments/save`, department)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  department$ = (departmentId: number) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.baseURL}/departments/get/${departmentId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (departmentId: number)=> <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.baseURL}/departments/delete/${departmentId}`)
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
  public update$ = (department: Department) => <Observable<CustomResponse>>
    this.http.put<CustomResponse>(`${this.baseURL}/departments/update`, department)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

}
