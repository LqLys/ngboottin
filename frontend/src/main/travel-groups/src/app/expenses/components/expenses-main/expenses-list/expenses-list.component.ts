import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ExpensesFacade} from '../../../facades/expenses.facade';
import {AuthService} from "../../../../auth/services/auth.service";

@Component({
  selector: 'app-expenses-list',
  templateUrl: './expenses-list.component.html',
  styleUrls: ['./expenses-list.component.css']
})
export class ExpensesListComponent implements OnInit {

  userBalance$ = this.expensesFacade.groupBalance$;

  cols: any[];

  constructor(public expensesFacade: ExpensesFacade, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    const username = this.authService.getAuthenticatedUsername();
    this.expensesFacade.loadUserGroupBalance(username).subscribe();

    this.cols = [
      {field: 'name', header: 'name'},
      {field: 'destination', header: 'destination'},
      {field: 'startDate', header: 'start date'},
      {field: 'endDate', header: 'end date'},
      {field: 'settledBalance', header: 'settled balance'}
    ];
  }


  onDelete(id: number) {
    // this.tripsFacade.deleteUser(id).subscribe();
  }

  onEdit(id: number) {
    this.router.navigateByUrl('/admin/edit-user/' + id);
  }

  getExpenseStyle(value: any, field: any) {
    let style = {'backgroundColor': 'white'};
    if(field === 'settledBalance'){
      style.backgroundColor = +value < 0 ? '#f74a4a': '#0ee32a';
    }
    return style;
  }
}
