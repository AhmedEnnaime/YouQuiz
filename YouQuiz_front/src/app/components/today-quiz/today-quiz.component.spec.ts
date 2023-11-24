import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodayQuizComponent } from './today-quiz.component';

describe('TodayQuizComponent', () => {
  let component: TodayQuizComponent;
  let fixture: ComponentFixture<TodayQuizComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TodayQuizComponent]
    });
    fixture = TestBed.createComponent(TodayQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
