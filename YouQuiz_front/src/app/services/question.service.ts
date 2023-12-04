import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { Observable, catchError } from 'rxjs';
import { ITempoQuiz } from '../shared/models/ITempoQuiz';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  private baseUrl: string = 'http://localhost:8082/api';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
    }),
  };

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getQuestionsByQuiz(quizID: number): Observable<ITempoQuiz[]> {
    return this.http
      .get<ITempoQuiz[]>(
        `${this.baseUrl}/questions/tempos/${quizID}`,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
