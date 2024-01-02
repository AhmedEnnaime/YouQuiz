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

  addTempo(tempo: ITempoQuiz): Observable<ITempoQuiz> {
    return this.http
      .post<ITempoQuiz>(
        `${this.baseUrl}/questions/tempo`,
        tempo,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

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
  ): Observable<{ message: string; deletedElementIdentifier: ITempoID }> {
    return this.http
      .delete<{
        message: string;
        deletedElementIdentifier: ITempoID;
      }>(
        `${this.baseUrl}/questions/${tempoID?.questionID}/tempo/${tempoID?.quizID}`,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

  updateTempo(
    tempo: ITempoQuiz,
    questionID: number,
    quizID: number
  ): Observable<ITempoQuiz> {
    return this.http
      .patch<ITempoQuiz>(
        `${this.baseUrl}/questions/tempo/${questionID}/quiz/${quizID}`,
        tempo,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
