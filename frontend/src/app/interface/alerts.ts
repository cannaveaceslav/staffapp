import {AlertType} from "../enum/alerttype";

export class Alerts {
    id!: string;
    type!: AlertType;
    message!: string;
    autoClose!: boolean;
    keepAfterRouteChange!: boolean;
    fade!: boolean;

    constructor(init?:Partial<Alerts>) {
        Object.assign(this, init);
    }
}
