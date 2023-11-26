import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Trainer } from '../shared/models/trainer.model';

@Injectable({
  providedIn: 'root',
})
export class TrainerService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  getTrainers(): Observable<Trainer[]> {
    return this.http.get<Trainer[]>(`${this.baseUrl}/trainers`);
  }
}
