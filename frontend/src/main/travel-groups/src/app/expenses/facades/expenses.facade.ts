import {Injectable} from '@angular/core';
import {ExpensesService} from '../services/expenses.service';
import {BehaviorSubject} from 'rxjs';
import {AddExpense, Expense, UserGroupBalance} from '../../models';
import {tap} from 'rxjs/operators';
import {AuthService} from '../../auth/services/auth.service';

@Injectable({providedIn: 'root'})
export class ExpensesFacade {

  expenses$: BehaviorSubject<Expense[]> = new BehaviorSubject<Expense[]>([]);
  groupBalance$: BehaviorSubject<UserGroupBalance[]> = new BehaviorSubject<UserGroupBalance[]>([])

  constructor(private expensesService: ExpensesService, private authService: AuthService) {
  }


  loadExpensesByUserId(userId: number) {
    // return this.expensesService.loadExpensesByUserId(userId).pipe(
    //   tap(data => this.expenses$.next(data))
    // );
  }

  loadExpenseByTripId(tripId: number) {
    return this.expensesService.loadExpenseByGroupId(tripId).pipe(
      tap(data => this.expenses$.next(data))
    );
  }

  addExpense(title: string, amount: number, groupId: number) {
    const username = this.authService.getAuthenticatedUsername();
    const addExpense: AddExpense = {amount: amount, title: title, groupId: groupId, username: username}
    return this.expensesService.addExpense(addExpense).pipe(
      tap(data => this.expenses$.next([...this.expenses$.value, data]))
    );
  }

  deleteExpense(selectedExpense: Expense) {
    return this.expensesService.deleteExpense(selectedExpense);

  }

  updateExpense(expense: Expense) {
    return this.expensesService.updateExpense(expense);
  }

  loadUserGroupBalance(username: string | string[]) {
    return this.expensesService.loadUserGroupBalance(username).pipe(
      tap(data => this.groupBalance$.next(data))
    );
  }
}
