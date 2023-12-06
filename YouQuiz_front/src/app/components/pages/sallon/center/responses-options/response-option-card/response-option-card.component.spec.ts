import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponseOptionCardComponent } from './response-option-card.component';

describe('ResponseOptionCardComponent', () => {
  let component: ResponseOptionCardComponent;
  let fixture: ComponentFixture<ResponseOptionCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResponseOptionCardComponent]
    });
    fixture = TestBed.createComponent(ResponseOptionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
