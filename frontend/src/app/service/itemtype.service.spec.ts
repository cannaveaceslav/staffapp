import { TestBed } from '@angular/core/testing';

import { ItemTypeService } from './itemtype.service';

describe('ItemTypeService', () => {
  let service: ItemTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemTypeService);
  });

  // it('should be created', () => {
  //   expect(service).toBeTruthy();
  // });
});
