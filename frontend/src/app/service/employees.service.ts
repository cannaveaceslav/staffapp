import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {
  private baseURL = 'http://localhost:8080';
  constructor(private httpClient: HttpClient) { }

  getEmployees() {
  console.log('Getting all employees');
  return this.httpClient.get(this.baseURL+'/employees')
}



}
