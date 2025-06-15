import {Component, OnInit} from '@angular/core';
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
export class AddEquipementComponent implements OnInit{

  equipementForm : FormGroup;
  equipements: any[] = [];
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
        next: (res) => this.fetchEquipements(),

        error: (err) => alert('Erreur lors de l’ajout : ' + err.message)
      });
    }
    }

   ngOnInit() :void{
    this.fetchEquipements();
   }

  fetchEquipements(): void {
    this.equipementService.displayEquipement().subscribe({
      next: (data) => {
        this.equipements = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des équipements', err);
      }
    });
  }


}
