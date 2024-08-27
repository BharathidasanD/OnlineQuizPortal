import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizRequestStatusComponent } from './quiz-request-status.component';

describe('QuizRequestStatusComponent', () => {
  let component: QuizRequestStatusComponent;
  let fixture: ComponentFixture<QuizRequestStatusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizRequestStatusComponent]
    });
    fixture = TestBed.createComponent(QuizRequestStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
