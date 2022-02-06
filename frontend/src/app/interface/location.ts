import {Employee} from "./employee";

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
}
