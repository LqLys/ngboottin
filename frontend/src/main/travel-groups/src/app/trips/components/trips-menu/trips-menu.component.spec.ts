
import { TripsMenuComponent } from './trips-menu.component';
import {async, ComponentFixture, TestBed} from "@angular/core/testing";

describe('TripsMenuComponent', () => {
  let component: TripsMenuComponent;
  let fixture: ComponentFixture<TripsMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TripsMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TripsMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
