import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TripsPageComponent} from './pages/trips-page/trips-page.component';
import {TripsMainComponent} from './components/trips-main/trips-main.component';
import {TripsAddComponent} from './components/trips-main/trips-add/trips-add.component';
import {TripDetailsComponent} from './components/trips-main/trip-details/trip-details.component';
import {TripChatComponent} from './components/trips-main/trip-chat/trip-chat.component';


const routes: Routes = [
  {
    path: '',
    component: TripsPageComponent,
    data: {
      breadcrumb: null
    },
    children: [
      {
        path: 'main',
        component: TripsMainComponent,
        data: {
          breadcrumb: 'main'
        }
      },
      {
        path: 'add-trip',
        component: TripsAddComponent,
        data: {
          breadcrumb: 'add-trip'
        }
      },
      {
        path: 'trip-details/:id',
        component: TripDetailsComponent,
        data: {
          breadcrumb: 'trip-details'
        }
      },
      {
        path: 'trip-chat/:id',
        component: TripChatComponent,
        data: {
          breadcrumb: 'trip-chat'
        }
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TripsRoutingModule {
}
