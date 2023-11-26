import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../shared/models/student.model';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.baseUrl}/students`);
  }
}
