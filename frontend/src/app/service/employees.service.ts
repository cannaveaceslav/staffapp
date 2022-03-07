import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Employee} from "../interface/employee";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../interface/custom-response";
import {catchError, tap} from "rxjs/operators";
import {environment} from "../../environments/environment";




@Injectable({
  providedIn: 'root'
})
export class EmployeesService {
  private baseURL = environment.serverUrl;
  formData?: Employee;


  constructor(private httpClient: HttpClient) {  }


  // public getEmployees() {
  //   console.log('Getting all employees');
  //   return this.httpClient.get(this.baseURL + '/employees')
  // }

  public getEmployees$ = <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/employees`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public getEmployee$ = (employeeId: number) => <Observable<CustomResponse>>
    this.httpClient.get<CustomResponse>(`${this.baseURL}/employees/get/${employeeId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  public save$ = (employee: Employee) => <Observable<CustomResponse>>
    this.httpClient.post<CustomResponse>(`${this.baseURL}/employees/save`, employee)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (employee: Employee) => <Observable<CustomResponse>>
    this.httpClient.delete<CustomResponse>(`${this.baseURL}/employees/delete/` + employee.id)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );


  private  handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    throw new Error(`An error occurred - Error code: ${error.status}`);
  }


}
