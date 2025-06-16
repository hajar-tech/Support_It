import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Equipement {
  idEquipement: number;
  nameEquipement: string;
  descriptionEquipement: string;
  dateEquipement: string;
  type: 'LAPTOP' | 'DESKTOP' | 'PRINTER' | 'SERVER';
  status: 'ACTIVE' | 'OBSOLETE' | 'OUT_OF_SERVICE';
}

@Injectable({
  providedIn: 'root'
})
export class EquipementService {
  private apiUrl = `${environment.apiUrl}/equipement`;

  constructor(private http: HttpClient) { }

  getAllEquipements(): Observable<Equipement[]> {
    return this.http.get<Equipement[]>(`${this.apiUrl}/all`);
  }

  getEquipementById(id: number): Observable<Equipement> {
    return this.http.get<Equipement>(`${this.apiUrl}/${id}`);
  }

  addEquipement(equipement: Equipement): Observable<Equipement> {
    return this.http.post<Equipement>(`${this.apiUrl}/add`, equipement);
  }

  updateEquipement(id: number, equipement: Equipement): Observable<Equipement> {
    return this.http.put<Equipement>(`${this.apiUrl}/${id}`, equipement);
  }
} 