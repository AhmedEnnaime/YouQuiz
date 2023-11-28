import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcomingQuizzesComponent } from './upcoming-quizzes.component';

describe('UpcomingQuizzesComponent', () => {
  let component: UpcomingQuizzesComponent;
  let fixture: ComponentFixture<UpcomingQuizzesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpcomingQuizzesComponent]
    });
    fixture = TestBed.createComponent(UpcomingQuizzesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
