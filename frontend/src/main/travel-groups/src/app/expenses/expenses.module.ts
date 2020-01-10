import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExpensesRoutingModule } from './expenses-routing.module';
import { ExpensesPageComponent } from './pages/expenses-page/expenses-page.component';
import { ExpensesMenuComponent } from './components/expenses-menu/expenses-menu.component';
import { ExpensesMainComponent } from './components/expenses-main/expenses-main.component';
import { ExpensesListComponent } from './components/expenses-main/expenses-list/expenses-list.component';
import {SharedModule} from '../shared/shared.module';


@NgModule({
  declarations: [ExpensesPageComponent, ExpensesMenuComponent, ExpensesMainComponent, ExpensesListComponent],
  imports: [
    CommonModule,
    ExpensesRoutingModule,
    SharedModule
  ]
})
export class ExpensesModule { }
