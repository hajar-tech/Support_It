import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Panne {
  idPanne: number;
  typePanne: string;
  descriptionPanne: string;
  datePanne: string;
  status: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED' | 'CLOSED';
}

@Injectable({
  providedIn: 'root'
})
export class PanneService {
  private apiUrl = `${environment.apiUrl}/panne`;

  constructor(private http: HttpClient) { }

  getAllPannes(): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/all`);
  }

  getPanneById(id: number): Observable<Panne> {
    return this.http.get<Panne>(`${this.apiUrl}/${id}`);
  }

  addPanne(panne: Panne): Observable<Panne> {
    return this.http.post<Panne>(`${this.apiUrl}/add`, panne);
  }

  updatePanne(id: number, panne: Panne): Observable<Panne> {
    return this.http.put<Panne>(`${this.apiUrl}/${id}`, panne);
  }
} 