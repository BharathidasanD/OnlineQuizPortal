import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestStatusComponent } from './request-status.component';

describe('RequestStatusComponent', () => {
  let component: RequestStatusComponent;
  let fixture: ComponentFixture<RequestStatusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RequestStatusComponent]
    });
    fixture = TestBed.createComponent(RequestStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});