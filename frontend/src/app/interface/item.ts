export interface Item {
  id: number;
  locationNumber: number;
  description: string;
  available: boolean;
  createdAt: Date;
  modifiedAt: Date;
  employeeId: number;
}
