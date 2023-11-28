import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { Student } from '../shared/models/student.model';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getStudents(): Observable<Student[]> {
    return this.http
      .get<Student[]>(`${this.baseUrl}/students`)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
