import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {filter, map, Observable, of} from "rxjs";
import {environment} from "../../environments/environment";
import {catchError, tap} from "rxjs/operators";
import {DataState} from "../enum/data-state.enum";
import {RegistrationRequest} from "../interface/registrationRequest";


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseURL = environment.serverUrl;
  public registrationRequest!: Observable<RegistrationRequest>;

  constructor(private httpClient: HttpClient) {
  }


  authenticate(username: string, password: string) {
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
    return this.httpClient.get<Boolean>(`${this.baseURL}/login/validateLogin`, {headers}).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          let authString = 'Basic ' + btoa(username + ':' + password);
          sessionStorage.setItem('basicauth', authString);
          return userData;
        }),
      catchError(this.handleError)
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

  register(registrationRequest: RegistrationRequest) {

    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa('admin' + ':' + 'admin')});
    return this.httpClient.post(`${this.baseURL}/registration`, registrationRequest, {headers});
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }
}
