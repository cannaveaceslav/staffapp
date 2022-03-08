import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentsAdminComponent } from './departments-admin.component';

describe('DepartmentsAdminComponent', () => {
  let component: DepartmentsAdminComponent;
  let fixture: ComponentFixture<DepartmentsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentsAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartmentsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
