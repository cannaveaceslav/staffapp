import { TestBed } from '@angular/core/testing';

import { ItemtypeService } from './itemtype.service';

describe('ItemtypeService', () => {
  let service: ItemtypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemtypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
