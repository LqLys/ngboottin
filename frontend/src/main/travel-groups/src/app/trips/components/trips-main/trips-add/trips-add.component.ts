import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {AdminUserFacade} from '../../../../admin/facades/admin-user.facade';
import {AddTripWithUsers, Trip, User, UserData} from '../../../../models';
import {TripsFacade} from '../../../facades/trips.facade';
import {Router} from '@angular/router';

@Component({
  selector: 'app-trips-add',
  templateUrl: './trips-add.component.html',
  styleUrls: ['./trips-add.component.css']
})
export class TripsAddComponent implements OnInit {

  users$ = this.userFacade.users$;
  invitedUsers: User[] = [];
  datesError: any = {isError: false, errorMessage: ''};
  cols: any[];

  tripForm = this.fb.group({
    name: ['', Validators.required],
    destination: ['', Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required]
  });


  constructor(private fb: FormBuilder, private userFacade: AdminUserFacade,
              private tripFacade: TripsFacade, private router: Router) {
  }

  ngOnInit() {
    this.userFacade.loadUsers().subscribe();
    this.cols = [
      {field: 'username', header: 'username'},
      {field: 'email', header: 'email'},
      {field: 'actions', header: 'actions'}
    ];
  }

  onSubmit() {
    console.log(this.tripForm.value);
    if (this.tripForm.valid) {
      const tripData: AddTripWithUsers = {
        name: this.tripForm.value.name,
        destination: this.tripForm.value.destination,
        startDate: this.tripForm.value.startDate,
        endDate: this.tripForm.value.endDate,
        userIds: this.invitedUsers.map(u => u.id)
      };
      this.tripFacade.addTripWithUsers(tripData).subscribe(() => {
        this.router.navigateByUrl('/trips/main');
      });
    }
  }

  onUserInvite(user: User) {
    if (!this.invitedUsers.find(u => u.id === user.id)) {
      this.invitedUsers.push({...user});
    }
  }

  onRemoveUser(user: User) {
    this.invitedUsers = this.invitedUsers.filter(u => u.id !== user.id);
  }

  compareTwoDates() {
    console.log('comparing');
    if (new Date(this.tripForm.controls.startDate.value) > new Date(this.tripForm.controls.endDate.value)) {
      this.datesError = {
        isError: true, errorMessage: 'End Date cannot be before start date'
      };
    } else {
      this.datesError = {
        isError: false
      };
    }
  }

  onGroupUninvite(id: number) {
    const uninvited = this.invitedUsers.filter(u => u.id === id);
    this.invitedUsers = this.invitedUsers.filter(u => u.id !== id);
    console.log(uninvited);
    console.log(this.users$.value);
    this.users$.next([...this.users$.value, ...uninvited]);
  }

  onGroupInvite(id: number) {
    if (!this.invitedUsers.find(u => u.id === id)) {
      const user = this.users$.value.find(u => u.id === id);
      this.invitedUsers.push({...user});
      const uninvited = this.users$.value.filter(u => u.id !== id);
      this.users$.next(uninvited);
    }
  }
}
