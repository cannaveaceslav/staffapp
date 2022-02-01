import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemtypeComponent } from './itemtype.component';

describe('ItemtypeComponent', () => {
  let component: ItemtypeComponent;
  let fixture: ComponentFixture<ItemtypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemtypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemtypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
