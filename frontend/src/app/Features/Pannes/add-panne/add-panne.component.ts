import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PanneService, Panne } from '../../../Service/panne.service';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-panne',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-panne.component.html'
})
export class AddPanneComponent implements OnInit {
  panneForm: FormGroup;
  pannes: Panne[] = [];

  constructor(private fb: FormBuilder, private panneService: PanneService) {
    this.panneForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.fetchPannes();
  }

  onSubmit() {
    if (this.panneForm.valid) {
      this.panneService.ajouterPanne(this.panneForm.value).subscribe({
        next: () => {
          this.panneForm.reset();
          this.fetchPannes();
        },
        error: (err) => console.error(err)
      });
    }
  }

  fetchPannes(): void {
    this.panneService.RecupererPannes().subscribe({
      next: (data) => this.pannes = data,
      error: (err) => console.error('Erreur chargement pannes :', err)
    });
  }
}
