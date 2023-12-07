import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizQuestionCardComponent } from './quiz-question-card.component';

describe('QuizQuestionCardComponent', () => {
  let component: QuizQuestionCardComponent;
  let fixture: ComponentFixture<QuizQuestionCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizQuestionCardComponent]
    });
    fixture = TestBed.createComponent(QuizQuestionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
