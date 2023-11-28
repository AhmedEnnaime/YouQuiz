import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Quiz } from '../shared/models/quiz.model';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getQuizzes(): Observable<Quiz[]> {
    return this.http
      .get<Quiz[]>(`${this.baseUrl}/quizzes`)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
