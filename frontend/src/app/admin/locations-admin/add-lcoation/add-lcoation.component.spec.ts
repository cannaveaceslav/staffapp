import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLcoationComponent } from './add-lcoation.component';

describe('AddLcoationComponent', () => {
  let component: AddLcoationComponent;
  let fixture: ComponentFixture<AddLcoationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLcoationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLcoationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
