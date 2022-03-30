import {Location} from "./location";
import {Employee} from "./employee";
import {Item} from "./item";
import {User} from "./user";
import {ItemType} from "./itemType";
import {Department} from "./department";
import {Company} from "./company";

export interface CustomResponse {
  timeStamp: Date;
  statusCode: number;
  status: string;
  reason: string;
  message: string;
  developerMessage: string;
  data: {locations?: Location [], location?: Location
        ,employees?: Employee [], employee?: Employee
        ,items?: Item [], item?: Item
        ,itemTypes?: ItemType [], itemType?: ItemType
        ,users?: User [] ,user?: User
        ,departments?: Department [] ,department?: Department
        ,companies?: Company [] ,company?: Company
        };
}
