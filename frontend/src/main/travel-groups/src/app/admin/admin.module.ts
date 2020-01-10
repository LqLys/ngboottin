import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { AdminMenuComponent } from './components/admin-menu/admin-menu.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';
import {SharedModule} from '../shared/shared.module';
import { AdminUsersListComponent } from './components/admin-users/admin-users-list/admin-users-list.component';
import { AdminUsersAddComponent } from './components/admin-users/admin-users-add/admin-users-add.component';
import { AdminUsersEditComponent } from './components/admin-users/admin-users-edit/admin-users-edit.component';


@NgModule({
  declarations: [AdminPageComponent, AdminMenuComponent, AdminUsersComponent, AdminUsersListComponent, AdminUsersAddComponent, AdminUsersEditComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
  ]
})
export class AdminModule { }
