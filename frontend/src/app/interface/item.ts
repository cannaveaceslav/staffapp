import {Employee} from "./employee";
import {ItemType} from "./itemType";
import {Location} from "./location";

export class Item {
  id!: number;
  barcode!: string;
  createdAt!: Date;
  description!: string;
  enabled!: boolean;
  image!: Blob;
  itemName!: string;
  manufacturedAt!: Date;
  modifiedAt!: Date;
  employee!: Employee;
  itemType!: ItemType;
  location!: Location;

}
