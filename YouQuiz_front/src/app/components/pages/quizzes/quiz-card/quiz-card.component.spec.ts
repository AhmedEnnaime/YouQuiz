import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizCardComponent } from './quiz-card.component';

describe('QuizCardComponent', () => {
  let component: QuizCardComponent;
  let fixture: ComponentFixture<QuizCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizCardComponent]
    });
    fixture = TestBed.createComponent(QuizCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
