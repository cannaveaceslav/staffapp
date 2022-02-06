import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap, catchError} from "rxjs/operators";
import {CustomResponse} from "../interface/custom-response";
import {environment} from "../../environments/environment";
import {User} from "../interface/user";


@Injectable({providedIn: 'root'})
export class UserService {
  private readonly baseURL = environment.serverUrl;

  constructor(private http: HttpClient) {
  }

  getUsers$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.baseURL}/users`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  save$ = (user: User) => <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.baseURL}/users/save`, user)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  user$ = (userId: number) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.baseURL}/users/get/${userId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (userId: number) => <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.baseURL}/users/delete/${userId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );


  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }
}
