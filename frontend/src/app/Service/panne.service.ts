import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
export interface Panne{
  id? : number
  name : string;
}

@Injectable({
  providedIn: 'root'
})

export class PanneService {
  private apiUrl = 'http://localhost:8081/Panne';

  constructor(private http: HttpClient) {}

  ajouterPanne(panne: Panne): Observable<Panne> {
    return this.http.post<Panne>(`${this.apiUrl}/add`, panne);
  }

  RecupererPannes(): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}`);
  }



}
