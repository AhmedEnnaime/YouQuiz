import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedQuizCardComponent } from './assigned-quiz-card.component';

describe('AssignedQuizCardComponent', () => {
  let component: AssignedQuizCardComponent;
  let fixture: ComponentFixture<AssignedQuizCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignedQuizCardComponent]
    });
    fixture = TestBed.createComponent(AssignedQuizCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
