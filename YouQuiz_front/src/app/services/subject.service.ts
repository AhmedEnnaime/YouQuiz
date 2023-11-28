import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from '../shared/models/subject.model';

@Injectable({
  providedIn: 'root',
})
export class SubjectService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  getSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.baseUrl}/subjects`);
  }
}
