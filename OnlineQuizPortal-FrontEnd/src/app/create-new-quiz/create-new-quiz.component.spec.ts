import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewQuizComponent } from './create-new-quiz.component';

describe('CreateNewQuizComponent', () => {
  let component: CreateNewQuizComponent;
  let fixture: ComponentFixture<CreateNewQuizComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateNewQuizComponent]
    });
    fixture = TestBed.createComponent(CreateNewQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
