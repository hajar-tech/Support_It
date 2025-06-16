import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { AppComponent } from './app/app.component';
import { TicketListComponent } from './app/components/ticket-list/ticket-list.component';
import {routes} from './app/app.routes';

/*
const routes: Routes = [
  { path: '', component: TicketListComponent },
  { path: 'tickets', loadComponent: () => import('./app/components/ticket-list/ticket-list.component').then(m => m.TicketListComponent) },
  { path: 'tickets/new', loadComponent: () => import('./app/components/new-ticket/new-ticket.component').then(m => m.NewTicketComponent) }
];

 */

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient()
  ]
}).catch(err => console.error(err));
