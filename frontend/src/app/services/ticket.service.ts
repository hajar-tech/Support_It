import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Equipement } from './equipement.service';
import { Panne } from './panne.service';

export interface Ticket {
  id: number;
  description: string;
  status: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED' | 'CLOSED';
  dateCreation: string;
  equipement: Equipement;
  panne: Panne;
}

export interface TicketRequest {
  description: string;
  equipementId: number;
  panneId?: number;
}

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = `${environment.apiUrl}/tickets`;

  constructor(private http: HttpClient) { }

  getAllTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/all`);
  }

  getTicketById(id: number): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.apiUrl}/${id}`);
  }

  createTicket(ticket: TicketRequest): Observable<Ticket> {
    return this.http.post<Ticket>(`${this.apiUrl}/create`, ticket);
  }

  updateTicketStatus(id: number, status: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/${id}/status`, { status });
  }

  getTicketsByTechnician(technicianId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/technician/${technicianId}`);
  }
} 