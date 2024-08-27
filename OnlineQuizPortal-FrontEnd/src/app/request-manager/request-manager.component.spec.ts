import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestManagerComponent } from './request-manager.component';

describe('RequestManagerComponent', () => {
  let component: RequestManagerComponent;
  let fixture: ComponentFixture<RequestManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RequestManagerComponent]
    });
    fixture = TestBed.createComponent(RequestManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
