import {Company} from "./company";
import {Department} from "./department";
import {Location} from "./location";

export class Employee {
  id!: number;
  company!: Company;
  firstName!: string;
  lastName!: string;
  email!: string;
  department!: Department;
  birthday!: Date;
  createdAt!: Date;
  modifiedAt!: Date;
  enabled!: boolean;
  image!: Blob;
  location!: Location;
}
