import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {PanneService} from '../../../Service/panne.service';

@Component({
  selector: 'app-add-panne',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-panne.component.html'
})
export class AddPanneComponent {
  panneForm: FormGroup;

  constructor(private fb: FormBuilder, private panneService: PanneService) {
    this.panneForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.panneForm.valid) {
      this.panneService.ajouterPanne(this.panneForm.value).subscribe({
        next: (res: any) => {
          console.log('Panne ajoutée avec succès', res);
          this.panneForm.reset();
        },
        error: (err: any) => console.error(err)
      });
    }
  }
}
