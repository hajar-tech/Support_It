import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {AddPanneComponent} from './Features/Pannes/add-panne/add-panne.component';
import {NavbareComponent} from './shared/navbare/navbare.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbareComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
