import { TestBed } from '@angular/core/testing';

import { AssignQuizService } from './assign-quiz.service';

describe('AssignQuizService', () => {
  let service: AssignQuizService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssignQuizService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
