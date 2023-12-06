import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionTypeButtonComponent } from './question-type-button.component';

describe('QuestionTypeButtonComponent', () => {
  let component: QuestionTypeButtonComponent;
  let fixture: ComponentFixture<QuestionTypeButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionTypeButtonComponent]
    });
    fixture = TestBed.createComponent(QuestionTypeButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
