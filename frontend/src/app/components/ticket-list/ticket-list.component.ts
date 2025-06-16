import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TicketService, Ticket } from '../../services/ticket.service';

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2>Support Tickets</h2>
      <button class="btn btn-primary" routerLink="/tickets/new">Create New Ticket</button>
    </div>

    <div class="table-responsive">
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Equipment</th>
            <th>Panne</th>
            <th>Status</th>
            <th>Created</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let ticket of tickets">
            <td>{{ticket.id}}</td>
            <td>{{ticket.description}}</td>
            <td>{{ticket.equipement.nameEquipement}}</td>
            <td>{{ticket.panne.typePanne}}</td>
            <td>
              <span [class]="'badge ' + getStatusClass(ticket.status)">
                {{ticket.status}}
              </span>
            </td>
            <td>{{ticket.dateCreation | date:'medium'}}</td>
            <td>
              <button class="btn btn-sm btn-outline-primary me-2" (click)="updateStatus(ticket)">
                Update Status
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    .badge {
      padding: 0.5em 1em;
    }
    .badge-OPEN { background-color: #ffc107; }
    .badge-IN_PROGRESS { background-color: #17a2b8; }
    .badge-RESOLVED { background-color: #28a745; }
    .badge-CLOSED { background-color: #6c757d; }
  `]
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getAllTickets().subscribe(
      tickets => this.tickets = tickets,
      error => console.error('Error loading tickets:', error)
    );
  }

  updateStatus(ticket: Ticket): void {
    const newStatus = this.getNextStatus(ticket.status);
    this.ticketService.updateTicketStatus(ticket.id, newStatus).subscribe(
      updatedTicket => {
        const index = this.tickets.findIndex(t => t.id === updatedTicket.id);
        if (index !== -1) {
          this.tickets[index] = updatedTicket;
        }
      },
      error => console.error('Error updating ticket status:', error)
    );
  }

  getStatusClass(status: string): string {
    return `badge-${status}`;
  }

  private getNextStatus(currentStatus: string): string {
    const statusFlow = ['OPEN', 'IN_PROGRESS', 'RESOLVED', 'CLOSED'];
    const currentIndex = statusFlow.indexOf(currentStatus);
    return statusFlow[Math.min(currentIndex + 1, statusFlow.length - 1)];
  }
} 