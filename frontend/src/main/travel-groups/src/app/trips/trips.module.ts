import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TripsRoutingModule } from './trips-routing.module';
import { TripsPageComponent } from './pages/trips-page/trips-page.component';
import { TripsMenuComponent } from './components/trips-menu/trips-menu.component';
import { TripsMainComponent } from './components/trips-main/trips-main.component';
import { TripsListComponent } from './components/trips-main/trips-list/trips-list.component';
import { TripsAddComponent } from './components/trips-main/trips-add/trips-add.component';
import {SharedModule} from '../shared/shared.module';
import { TripDetailsComponent } from './components/trips-main/trip-details/trip-details.component';
import { TripChatComponent } from './components/trips-main/trip-chat/trip-chat.component';


@NgModule({
  declarations: [TripsPageComponent, TripsMenuComponent, TripsMainComponent, TripsListComponent, TripsAddComponent, TripDetailsComponent, TripChatComponent],
  imports: [
    CommonModule,
    TripsRoutingModule,
    SharedModule
  ]
})
export class TripsModule { }
