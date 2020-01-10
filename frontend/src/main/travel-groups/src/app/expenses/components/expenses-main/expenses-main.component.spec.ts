import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpensesMainComponent } from './expenses-main.component';

describe('ExpensesMainComponent', () => {
  let component: ExpensesMainComponent;
  let fixture: ComponentFixture<ExpensesMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpensesMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpensesMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
