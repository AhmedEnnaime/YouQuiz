import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Level } from '../shared/models/level.model';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root',
})
export class LevelService {
  private baseUrl: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getLevels(): Observable<Level[]> {
    return this.http
      .get<Level[]>(`${this.baseUrl}/levels`)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }

  deleteLevel(id: number): Observable<any> {
    return this.http
      .delete<any>(`${this.baseUrl}/levels/${id}`)
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
