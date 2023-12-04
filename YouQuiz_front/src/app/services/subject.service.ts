import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Subject } from '../shared/models/subject.model';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root',
})
export class SubjectService {
  private baseUrl: string = 'http://localhost:8082/api';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
    }),
  };

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getSubjects(): Observable<Subject[]> {
    return this.http
      .get<Subject[]>(`${this.baseUrl}/subjects`, this.httpOptions)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
