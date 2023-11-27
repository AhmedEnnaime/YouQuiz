import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AssignQuiz } from '../shared/models/assign-quiz.model';

@Injectable({
  providedIn: 'root',
})
export class AssignQuizService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  getAssignments(): Observable<AssignQuiz[]> {
    return this.http.get<AssignQuiz[]>(`${this.baseUrl}/assignQuiz`);
  }
}
