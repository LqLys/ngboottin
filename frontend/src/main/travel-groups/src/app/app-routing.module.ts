import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './shared/components/home/home.component';
import {LoginComponent} from './auth/components/login/login.component';
import {RegisterComponent} from './auth/components/register/register.component';
import {AuthGuard} from "./auth/guards/auth.guard";
import {AdminGuard} from "./auth/guards/admin.guard";


const routes: Routes = [

  {
    path: '',
    component: HomeComponent,
    data: {
      breadcrumb: null
    },
  },
  {
    path: 'trips',
    canActivate: [AuthGuard],
    loadChildren: () => import('./trips/trips.module').then(t => t.TripsModule),
    data: {
      breadcrumb: 'trips'
    },
  },
  {
    path: 'expenses',
    canActivate: [AuthGuard],
    loadChildren: () => import('./expenses/expenses.module').then(e => e.ExpensesModule),
    data: {
      breadcrumb: 'expenses'
    },
  },
  {
    path: 'admin',
    canActivate: [AdminGuard],
    loadChildren: () => import('./admin/admin.module').then(a => a.AdminModule),
    data: {
      breadcrumb: 'admin'
    },
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      breadcrumb: 'login'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      breadcrumb: 'register'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
