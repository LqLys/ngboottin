import {Component, OnInit} from '@angular/core';
import {EditGroup, Expense, User, UserTrip} from '../../../../models';
import {FormBuilder, Validators} from '@angular/forms';
import {AdminUserFacade} from '../../../../admin/facades/admin-user.facade';
import {ExpensesFacade} from '../../../../expenses/facades/expenses.facade';
import {TripsFacade} from '../../../facades/trips.facade';
import {ActivatedRoute, Router} from '@angular/router';
import {FilterUtils} from 'primeng/utils';
import {AuthService} from '../../../../auth/services/auth.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-trip-details',
  templateUrl: './trip-details.component.html',
  styleUrls: ['./trip-details.component.css']
})
export class TripDetailsComponent implements OnInit {


  userTrip$ = this.tripFacade.userTrip$;

  expenses$ = this.expenseFacade.expenses$;
  trip;
  datesError: any = {isError: false, errorMessage: ''};
  cols: any[];

  invitedUsers1: UserTrip[];

  invitedUsers2: UserTrip[];

  clonedUsers: { [s: string]: UserTrip; } = {};

  tripForm = this.fb.group({
    name: ['', Validators.required],
    destination: ['', Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required]
  });

  expenseForm = this.fb.group({
    name: ['', Validators.required],
    amount: ['', [Validators.min(0), Validators.required]]
  });

  displayDialog: boolean;

  expense: Expense = {};

  selectedExpense: Expense;

  newExpense: boolean;

  expenses: Expense[];

  expenseCols: any[];

  totalExpenses = 0;

  constructor(private fb: FormBuilder, private userFacade: AdminUserFacade,
              private expenseFacade: ExpensesFacade, private tripFacade: TripsFacade,
              private route: ActivatedRoute, private authService: AuthService,
              private router: Router, private messageService: MessageService) {
  }

  ngOnInit() {
    this.tripFacade.loadTripById(this.route.snapshot.params.id).subscribe(data => {
      this.trip = data;
      this.tripForm.setValue({
        name: data.name,
        destination: data.destination,
        startDate: data.startDate,
        endDate: data.endDate
      });
    });
    this.tripFacade.loadUserTrips(this.route.snapshot.params.id).subscribe(() => {
      this.invitedUsers1 = this.userTrip$.value;
      this.invitedUsers2 = this.userTrip$.value;
    });
    this.expenseFacade.loadExpenseByTripId(this.route.snapshot.params.id).subscribe(data => {
      this.expenses = data;
      this.updateTotalExpenses();
    });

    this.cols = [
      {field: 'username', header: 'username'},
      {field: 'email', header: 'email'},
      {field: 'participation', header: 'participation'}
    ];

    this.expenseCols = [
      {field: 'title', header: 'name'},
      {field: 'amount', header: 'amount'}
    ];

    FilterUtils['custom'] = (value, filter): boolean => {
      if (filter === undefined || filter === null || filter.trim() === '') {
        return true;
      }

      if (value === undefined || value === null) {
        return false;
      }

      return parseInt(filter) > value;
    };
  }

  onRowEditInit(userTrip: UserTrip) {
    console.log('editing');
    this.clonedUsers[userTrip.username] = {...userTrip};
  }

  onRowEditSave(userTrip: UserTrip, index: number) {
    const partSum = this.invitedUsers1.map(u => u.participation).reduce((a, b) => a + b);
    console.log(partSum);
    if (100 - partSum > Math.abs(0.001)) {
      this.onRowEditCancel(userTrip, index);
    } else {
      this.tripFacade.updateParticipation(userTrip);
      delete this.clonedUsers[userTrip.username];
    }

    // this.messageService.add({severity:'success', summary: 'Success', detail:'Car is updated'});

    // else {
    //   this.messageService.add({severity:'error', summary: 'Error', detail:'Year is required'});
    // }
  }

  onRowEditCancel(userTrip: UserTrip, index: number) {
    this.invitedUsers2[index] = this.clonedUsers[userTrip.username];
    delete this.clonedUsers[userTrip.username];
  }


  onSubmit() {
    console.log(this.tripForm.value);
    if (this.tripForm.valid) {
      const editGroup: EditGroup = {
        id: this.route.snapshot.params.id,
        name: this.tripForm.value.name,
        destination: this.tripForm.value.destination,
        startDate: this.tripForm.value.startDate,
        endDate: this.tripForm.value.endDate
      };
      this.tripFacade.editGroup(editGroup).subscribe(() => {
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Group updated'});
      });
    }
  }


  onAddExpense() {
    console.log('adding expense');
    const groupId = this.route.snapshot.params.id;
    const amount = this.expenseForm.controls.amount.value;
    const title = this.expenseForm.controls.name.value;
    this.expenseFacade.addExpense(title, amount, groupId).subscribe(() => {
      this.expenses = this.expenses$.value;
      this.updateTotalExpenses();
      this.expenseForm.reset();
    });

  }

  log() {
    console.log(this.expenseForm);
  }

  compareTwoDates() {
    if (new Date(this.tripForm.controls.startDate.value) > new Date(this.tripForm.controls.endDate.value)) {
      this.datesError = {
        isError: true, errorMessage: 'End Date can\'t before start date'
      };
    } else {
      this.datesError = {
        isError: false
      };
    }
  }

  showDialogToAdd() {
    this.newExpense = true;
    this.expense = {};
    this.displayDialog = true;
  }

  save() {
    let expenses = [...this.expenses];
    if (this.newExpense) {
      expenses.push(this.expense);
    } else {
      expenses[this.expenses.indexOf(this.selectedExpense)] = this.expense;
      this.expenseFacade.updateExpense(this.expense).subscribe();
    }


    this.expenses = expenses;
    this.expense = null;
    this.displayDialog = false;
    this.updateTotalExpenses();
  }

  delete() {
    let index = this.expenses.indexOf(this.selectedExpense);
    this.expenses = this.expenses.filter((val, i) => i != index);
    this.expenseFacade.deleteExpense(this.selectedExpense).subscribe(() => {
      this.expenses$.next(this.expenses);
    });
    // this.expenses = null;
    this.displayDialog = false;
    this.updateTotalExpenses();
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  onRowSelect(event) {
    this.newExpense = false;
    this.expense = this.cloneExpense(event.data);
    this.displayDialog = true;
  }

  cloneExpense(c: Expense): Expense {
    let expense = {};
    for (let prop in c) {
      expense[prop] = c[prop];
    }
    return expense;
  }

  private updateTotalExpenses() {
    this.totalExpenses = this.expenses.length > 0 ? this.expenses.map(e => e.amount).reduce((a, b) => +a + +b) : 0;
  }

  onRemoveMember(rowData) {
    this.tripFacade.removeMember(rowData.id).subscribe(() => {
      this.invitedUsers1 = this.userTrip$.value.filter(a => a.id != rowData.id);
    });

  }

  onSettle() {
    const groupId = this.route.snapshot.params.id;
    this.tripFacade.settleByGroupId(groupId).subscribe();
  }

  onViewChat() {
    const groupId = this.route.snapshot.params.id;
    this.router.navigateByUrl('/trips/trip-chat/' + groupId);
  }
}
