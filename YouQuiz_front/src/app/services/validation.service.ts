import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { Observable, catchError } from 'rxjs';
import { IValidation } from '../shared/models/IValidation';

@Injectable({
  providedIn: 'root',
})
export class ValidationService {
  private baseUrl: string = 'http://localhost:8082/api';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
    }),
  };

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getResponsesByQuestion(questionID: number): Observable<IValidation[]> {
    return this.http
      .get<IValidation[]>(
        `${this.baseUrl}/validations/responses/${questionID}`,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
