import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { filter } from 'rxjs/operators';
import {Alerts} from "../interface/alerts";
import {AlertType} from "../enum/alerttype";


@Injectable({ providedIn: 'root' })
export class AlertService {
    private subject = new Subject<Alerts>();
    private defaultId = 'default-alert';

    // enable subscribing to alerts observable
    onAlert(id = this.defaultId): Observable<Alerts> {
        return this.subject.asObservable().pipe(filter(x => x && x.id === id));
    }

    // convenience methods
    success(message: string, options?: any) {
        this.alert(new Alerts({ ...options, type: AlertType.Success, message }));
    }

    error(message: string, options?: any) {
        this.alert(new Alerts({ ...options, type: AlertType.Error, message }));
    }

    info(message: string, options?: any) {
        this.alert(new Alerts({ ...options, type: AlertType.Info, message }));
    }

    warn(message: string, options?: any) {
        this.alert(new Alerts({ ...options, type: AlertType.Warning, message }));
    }

    // main alert method
    alert(alert: Alerts) {
        alert.id = alert.id || this.defaultId;
        this.subject.next(alert);
    }

    // clear alerts
    clear(id = this.defaultId) {
        this.subject.next(new Alerts({ id }));
    }
}
