import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Equipement, EquipementService} from '../../../Service/equipement.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-add-equipement',
  imports: [
    ReactiveFormsModule , CommonModule
  ],
  templateUrl: './add-equipement.component.html',
  styleUrl: './add-equipement.component.css'
})
export class AddEquipementComponent {

  equipementForm : FormGroup;
  isModalOpen = false;
  constructor(private fb: FormBuilder, private equipementService : EquipementService) {
    this.equipementForm = this.fb.group({
      nameEquipement: ['', Validators.required],
      descriptionEquipement: [''],
      dateEquipement: ['', Validators.required],
      type: ['', Validators.required],
      status: ['', Validators.required]
    })
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.equipementForm.reset(); // facultatif
  }

  onSubmit(){
    if (this.equipementForm.valid){
      const newEquipement : Equipement = this.equipementForm.value;

      this.equipementService.addEquipement(newEquipement).subscribe({
        next: (res) => alert('Équipement ajouté avec succès !'),
        error: (err) => alert('Erreur lors de l’ajout : ' + err.message)
      });
    }
    }




}
