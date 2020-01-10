import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AdminPageComponent} from './pages/admin-page/admin-page.component';
import {AdminUsersComponent} from './components/admin-users/admin-users.component';
import {AdminUsersAddComponent} from './components/admin-users/admin-users-add/admin-users-add.component';
import {AdminUsersEditComponent} from './components/admin-users/admin-users-edit/admin-users-edit.component';


const routes: Routes = [{
  path: '',
  component: AdminPageComponent,
  children: [
    {
    path: 'users',
    component: AdminUsersComponent,
    data: {
      breadcrumb: 'users'
    }
  },
    {
      path: 'add-user',
      component: AdminUsersAddComponent,
      data: {
        breadcrumb: 'add-user'
      }
    },
    {
      path: 'edit-user/:id',
      component: AdminUsersEditComponent,
      data: {
        breadcrumb: 'edit-user'
      }
    }
  ],
  data: {
    breadcrumb: null
  },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
