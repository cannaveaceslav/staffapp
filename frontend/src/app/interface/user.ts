import {UserRole} from "./userRole";

export class User {
  id!: number;
  firstName!: string;
  lastName!: string;
  email!: string;
  password!: string;
  userRole!: UserRole;
  locked!: boolean;
  enabled!: boolean;
}
