import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TicketService, TicketRequest } from '../../services/ticket.service';
import { EquipementService, Equipement } from '../../services/equipement.service';
import { PanneService, Panne } from '../../services/panne.service';

@Component({
  selector: 'app-new-ticket',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div class="card">
      <div class="card-header">
        <h2>Create New Support Ticket</h2>
      </div>
      <div class="card-body">
        <form [formGroup]="ticketForm" (ngSubmit)="onSubmit()">
          <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea
              id="description"
              formControlName="description"
              class="form-control"
              rows="3"
              [class.is-invalid]="ticketForm.get('description')?.invalid && ticketForm.get('description')?.touched"
            ></textarea>
            <div class="invalid-feedback" *ngIf="ticketForm.get('description')?.errors?.['required']">
              Description is required
            </div>
          </div>

          <div class="mb-3">
            <label for="equipement" class="form-label">Equipment</label>
            <select
              id="equipement"
              formControlName="equipementId"
              class="form-select"
              [class.is-invalid]="ticketForm.get('equipementId')?.invalid && ticketForm.get('equipementId')?.touched"
            >
              <option value="">Select Equipment</option>
              <option *ngFor="let equip of equipements" [value]="equip.idEquipement">
                {{equip.nameEquipement}} ({{equip.type}})
              </option>
            </select>
            <div class="invalid-feedback" *ngIf="ticketForm.get('equipementId')?.errors?.['required']">
              Please select an equipment
            </div>
          </div>

          <div class="mb-3">
            <label for="panne" class="form-label">Issue Type (Optional)</label>
            <select
              id="panne"
              formControlName="panneId"
              class="form-select"
            >
              <option value="">Select Issue Type</option>
              <option *ngFor="let panne of pannes" [value]="panne.idPanne">
                {{panne.typePanne}}
              </option>
            </select>
          </div>

          <div class="d-flex justify-content-end gap-2">
            <button type="button" class="btn btn-secondary" (click)="goBack()">Cancel</button>
            <button type="submit" class="btn btn-primary" [disabled]="ticketForm.invalid">
              Create Ticket
            </button>
          </div>
        </form>
      </div>
    </div>
  `,
  styles: [`
    .card {
      max-width: 800px;
      margin: 0 auto;
    }
  `]
})
export class NewTicketComponent implements OnInit {
  ticketForm: FormGroup;
  equipements: Equipement[] = [];
  pannes: Panne[] = [];

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService,
    private equipementService: EquipementService,
    private panneService: PanneService,
    private router: Router
  ) {
    this.ticketForm = this.fb.group({
      description: ['', Validators.required],
      equipementId: ['', Validators.required],
      panneId: ['']
    });
  }

  ngOnInit(): void {
    this.loadEquipements();
    this.loadPannes();
  }

  loadEquipements(): void {
    this.equipementService.getAllEquipements().subscribe(
      equipements => this.equipements = equipements,
      error => console.error('Error loading equipments:', error)
    );
  }

  loadPannes(): void {
    this.panneService.getAllPannes().subscribe(
      pannes => this.pannes = pannes,
      error => console.error('Error loading pannes:', error)
    );
  }

  onSubmit(): void {
    if (this.ticketForm.valid) {
      const ticketRequest: TicketRequest = this.ticketForm.value;
      this.ticketService.createTicket(ticketRequest).subscribe(
        () => {
          this.router.navigate(['/tickets']);
        },
        error => console.error('Error creating ticket:', error)
      );
    }
  }

  goBack(): void {
    this.router.navigate(['/tickets']);
  }
} 