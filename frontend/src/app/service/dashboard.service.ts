import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {SystemCPU} from "../interface/SystemCPU";
import {SystemlHealth} from "../interface/system-health";
import {environment} from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  // private baseUrl = 'http://localhost:8080/actuator';
  private baseUrl = environment.serverActuatorUrl

  constructor(private http:HttpClient) { }

  getHttpTraces():Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/httptrace`);
  }

  getSystemHealth():Observable<SystemlHealth>{
    return this.http.get<SystemlHealth>(`${this.baseUrl}/health`);
  }

  getSystemCPU():Observable<SystemCPU>{
    return this.http.get<SystemCPU>(`${this.baseUrl}/metrics/system.cpu.count`);
  }

  getProcessUptime():Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/metrics/process.uptime`);
  }
}
