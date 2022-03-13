import {Company} from "./company";
import {Department} from "./department";
import {Location} from "./location";
import {Serializer} from "@angular/compiler";
import {serialize} from "@angular/compiler/src/i18n/serializers/xml_helper";

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
  image!: string;
  location!: Location;
}
