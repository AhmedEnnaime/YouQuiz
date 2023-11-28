import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Trainer } from '../shared/models/trainer.model';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root',
})
export class TrainerService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getTrainers(): Observable<Trainer[]> {
    return this.http
      .get<Trainer[]>(`${this.baseUrl}/trainers`)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
