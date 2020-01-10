import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ExpensesPageComponent} from './pages/expenses-page/expenses-page.component';
import {ExpensesMainComponent} from './components/expenses-main/expenses-main.component';


const routes: Routes = [{
  path: '',
  component: ExpensesPageComponent,
  data: {
    breadcrumb: null
  },
  children: [{
  path: 'main',
  component: ExpensesMainComponent,
  data: {
    breadcrumb: 'main'
  }
}]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExpensesRoutingModule { }
