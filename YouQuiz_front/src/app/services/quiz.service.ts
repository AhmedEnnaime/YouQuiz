import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Quiz } from '../shared/models/quiz.model';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  private baseUrl: string = 'http://localhost:8082/api';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getQuizzes(): Observable<Quiz[]> {
    return this.http
      .get<Quiz[]>(`${this.baseUrl}/quizzes`)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

  addQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http
      .post<Quiz>(`${this.baseUrl}/quizzes`, quiz, this.httpOptions)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

  deleteQuiz(
    id: number
  ): Observable<{ message: string; deletedElementIdentifier: number }> {
    return this.http
      .delete<{ message: string; deletedElementIdentifier: number }>(
        `${this.baseUrl}/quizzes/${id}`,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

  getQuizByID(id: number): Observable<Quiz> {
    return this.http
      .get<Quiz>(`${this.baseUrl}/quizzes/${id}`, this.httpOptions)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
