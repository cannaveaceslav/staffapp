import {Employee} from "./employee";
import {LocationType} from "./LocationType";

export class Location {
  id!: number;
  locationNumber!: number;
  description!: string;
  available!: boolean;
  createdAt!: Date;
  modifiedAt!: Date;
  employee!: Employee;
  pos_x!: number;
  pos_y!: number;
  locationType!: LocationType;
}
