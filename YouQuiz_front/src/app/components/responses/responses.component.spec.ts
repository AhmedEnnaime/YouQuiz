import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsesComponent } from './responses.component';

describe('ResponsesComponent', () => {
  let component: ResponsesComponent;
  let fixture: ComponentFixture<ResponsesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResponsesComponent]
    });
    fixture = TestBed.createComponent(ResponsesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
