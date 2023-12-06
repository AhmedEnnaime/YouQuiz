import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsesOptionsComponent } from './responses-options.component';

describe('ResponsesOptionsComponent', () => {
  let component: ResponsesOptionsComponent;
  let fixture: ComponentFixture<ResponsesOptionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResponsesOptionsComponent]
    });
    fixture = TestBed.createComponent(ResponsesOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
