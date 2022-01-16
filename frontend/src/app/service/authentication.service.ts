import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {filter, map, Observable} from "rxjs";
import {CustomResponse} from "../interface/custom-response";
import {environment} from "../../environments/environment";
import {User} from "../interface/user";
import {catchError, tap} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseURL = environment.serverUrl;

  constructor(private httpClient: HttpClient) {
  }


  authenticate(username: string, password: string) {
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
    return this.httpClient.get<User>(`${this.baseURL}/login/validateLogin`, {headers}).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          return userData;
        }
      )
    );
  }


  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }
}
