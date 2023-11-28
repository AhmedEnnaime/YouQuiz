import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcomingQuizCardComponent } from './upcoming-quiz-card.component';

describe('UpcomingQuizCardComponent', () => {
  let component: UpcomingQuizCardComponent;
  let fixture: ComponentFixture<UpcomingQuizCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpcomingQuizCardComponent]
    });
    fixture = TestBed.createComponent(UpcomingQuizCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
