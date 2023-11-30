import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LevelModalComponent } from './level-modal.component';

describe('LevelModalComponent', () => {
  let component: LevelModalComponent;
  let fixture: ComponentFixture<LevelModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LevelModalComponent]
    });
    fixture = TestBed.createComponent(LevelModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
