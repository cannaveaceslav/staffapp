import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private SERVER_URL = environment.serverActuatorUrl;

  constructor(private httpClient: HttpClient) { }

   public getHttpTraces(): Observable<any>{
    return this.httpClient.get(`${this.SERVER_URL}/httptrace`);
   }


}
