import { TestBed } from '@angular/core/testing';

import { BasicauthhttpinterceptorService } from './basicauthhttpinterceptor.service';

describe('BasicauthhttpinterceptorService', () => {
  let service: BasicauthhttpinterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicauthhttpinterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
