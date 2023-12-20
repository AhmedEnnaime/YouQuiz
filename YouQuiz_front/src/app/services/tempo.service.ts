import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { ITempoID } from '../shared/models/ITempoID';
import { Observable, catchError } from 'rxjs';
import { ITempoQuiz } from '../shared/models/ITempoQuiz';

@Injectable({
  providedIn: 'root',
})
export class TempoService {
  private baseUrl: string = 'http://localhost:8082/api';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
    }),
  };

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getQuestionsByQuiz(tempoID: ITempoID): Observable<ITempoQuiz[]> {
    return this.http
      .get<ITempoQuiz[]>(
        `${this.baseUrl}/questions/tempos/${tempoID.quizID}`,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

  detachQuestionFromQuiz(
    tempoID: ITempoID | undefined
  ): Observable<{ message: string; deletedElementIdentifier: number }> {
    return this.http
      .delete<{
        message: string;
        deletedElementIdentifier: number;
      }>(
        `${this.baseUrl}/questions/${tempoID?.questionID}/tempo/${tempoID?.quizID}`,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
