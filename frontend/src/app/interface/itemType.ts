export interface ItemType {
  id: number;
  typeName: string;
  description: string;
  image: Blob;
  createdAt: Date;
  modifiedAt: Date;
  enabled: boolean;
}
