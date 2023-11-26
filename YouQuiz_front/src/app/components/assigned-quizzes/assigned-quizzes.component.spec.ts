import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedQuizzesComponent } from './assigned-quizzes.component';

describe('AssignedQuizzesComponent', () => {
  let component: AssignedQuizzesComponent;
  let fixture: ComponentFixture<AssignedQuizzesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignedQuizzesComponent]
    });
    fixture = TestBed.createComponent(AssignedQuizzesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
