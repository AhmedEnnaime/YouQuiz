import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodayQuizCardComponent } from './today-quiz-card.component';

describe('TodayQuizCardComponent', () => {
  let component: TodayQuizCardComponent;
  let fixture: ComponentFixture<TodayQuizCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TodayQuizCardComponent]
    });
    fixture = TestBed.createComponent(TodayQuizCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
