import {Company} from "./company";

export interface Employee {
  id: number;
  company: Company;
  firstName: string;
  lastName: string;
  email: string;
  department: Object;
  birthday: Date;
  createdAt: Date;
  modifiedAt: Date;
  enabled: boolean;
  image: Blob;
  location: Object;
}
