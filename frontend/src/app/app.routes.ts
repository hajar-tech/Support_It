import { Routes } from '@angular/router';
import {AddPanneComponent} from './Features/Pannes/add-panne/add-panne.component';
import {AddEquipementComponent} from './Features/Equipement/add-equipement/add-equipement.component';

export const routes: Routes = [
  {
    path:'panne',
    component: AddPanneComponent
  },
  {
    path : "addEquipement" ,
    component :AddEquipementComponent
  }
];
