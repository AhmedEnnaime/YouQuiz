import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quiz } from '../shared/models/quiz.model';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  getQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.baseUrl}/quizzes`);
  }
}
