import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponseCardComponent } from './response-card.component';

describe('ResponseCardComponent', () => {
  let component: ResponseCardComponent;
  let fixture: ComponentFixture<ResponseCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResponseCardComponent]
    });
    fixture = TestBed.createComponent(ResponseCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
