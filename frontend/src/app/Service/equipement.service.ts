import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface Equipement {
  idEquipement? : number;
  nameEquipement: string;
  descriptionEquipement: string;
  dateEquipement: string; // format (yyyy-mm-dd)
  type: 'LAPTOP' | 'DESKTOP' | 'PRINTER' | 'SERVER';
  status: 'ACTIVE' | 'OBSOLETE' | 'OUT_OF_SERVICE';
}

@Injectable({
  providedIn: 'root'
})
export class EquipementService {

  private apiUrl = 'http://localhost:8080/equipement';

  constructor(private http : HttpClient) { }

  addEquipement (equipement : Equipement) : Observable<Equipement>{
    return this.http.post<Equipement>(`${this.apiUrl}/add`,equipement);
  }
}
