import { Injectable } from '@angular/core';
import {AddExpense, Expense, UserGroupBalance} from '../../models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ExpensesService {

  private apiBase = environment.API_BASE_PATH;

  constructor(private http: HttpClient) { }



  // loadExpensesByUserId(userId: number): Observable<Expense[]> {
    // return new Observable<Expense[]>(obs => {
    //   obs.next(this.expenses);
    // });
  // }

  loadExpenseByGroupId(tripId: number): Observable<Expense[]> {
    return this.http.get<Expense[]>(this.apiBase + '/expense/group/' + tripId);
  }

  addExpense(addExpense: AddExpense): Observable<Expense> {
    return this.http.post<any>(this.apiBase + '/expense', addExpense);
  }

  deleteExpense(selectedExpense: Expense) {
    return this.http.delete<any>(this.apiBase + '/expense/' + selectedExpense.id);

  }

  updateExpense(expense: Expense) {
    const updateExpense = {id: expense.id, title: expense.title, amount: expense.amount};
    return this.http.put<any>(this.apiBase + '/expense', updateExpense);

  }

  loadUserGroupBalance(username: string | string[]): Observable<UserGroupBalance[]> {
    return this.http.get<UserGroupBalance[]>(this.apiBase + '/expense/balance/' + username);
  }
}
