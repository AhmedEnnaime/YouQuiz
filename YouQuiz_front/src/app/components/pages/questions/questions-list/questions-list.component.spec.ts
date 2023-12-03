import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionsListComponent } from './questions-list.component';

describe('QuestionsListComponent', () => {
  let component: QuestionsListComponent;
  let fixture: ComponentFixture<QuestionsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionsListComponent]
    });
    fixture = TestBed.createComponent(QuestionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
