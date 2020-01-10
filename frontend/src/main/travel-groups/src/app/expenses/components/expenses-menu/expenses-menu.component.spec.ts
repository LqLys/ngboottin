import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpensesMenuComponent } from './expenses-menu.component';

describe('ExpensesMenuComponent', () => {
  let component: ExpensesMenuComponent;
  let fixture: ComponentFixture<ExpensesMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpensesMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpensesMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
