import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SallonComponent } from './sallon.component';

describe('SallonComponent', () => {
  let component: SallonComponent;
  let fixture: ComponentFixture<SallonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SallonComponent]
    });
    fixture = TestBed.createComponent(SallonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
