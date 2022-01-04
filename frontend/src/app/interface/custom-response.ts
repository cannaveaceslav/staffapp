import {Location} from "./location";
import {Employee} from "./employee";
import {Item} from "./item";

export interface CustomResponse {
  timeStamp: Date;
  statusCode: number;
  status: string;
  reason: string;
  message: string;
  developerMessage: string;
  data: {locations?: Location [], location?: Location
        ,employees?: Employee [], employee?: Employee
        ,items?: Item [], item?: Item};
}
