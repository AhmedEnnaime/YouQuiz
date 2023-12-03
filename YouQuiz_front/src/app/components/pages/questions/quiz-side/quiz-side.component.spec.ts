import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizSideComponent } from './quiz-side.component';

describe('QuizSideComponent', () => {
  let component: QuizSideComponent;
  let fixture: ComponentFixture<QuizSideComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizSideComponent]
    });
    fixture = TestBed.createComponent(QuizSideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
