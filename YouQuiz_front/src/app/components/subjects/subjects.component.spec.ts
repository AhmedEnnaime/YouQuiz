import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectsComponent } from './subjects.component';

describe('SubjectsComponent', () => {
  let component: SubjectsComponent;
  let fixture: ComponentFixture<SubjectsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SubjectsComponent]
    });
    fixture = TestBed.createComponent(SubjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
