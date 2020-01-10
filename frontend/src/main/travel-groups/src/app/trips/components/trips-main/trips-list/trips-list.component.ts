import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TripsFacade} from '../../../facades/trips.facade';
import {Trip} from '../../../../models';
import {FilterUtils} from 'primeng/utils';
import {ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-trips-list',
  templateUrl: './trips-list.component.html',
  styleUrls: ['./trips-list.component.css']
})
export class TripsListComponent implements OnInit {

  trips$ = this.tripsFacade.trips$;

  trips: Trip[] = [];

  cols: any[];

  constructor(public tripsFacade: TripsFacade, private router: Router, private confirmationService: ConfirmationService) {
  }


  ngOnInit() {
    this.tripsFacade.loadAllTrips().subscribe(data => {
      this.trips = this.trips$.value;
      console.log(this.trips);
    });

    this.cols = [
      {field: 'id', header: 'ID'},
      {field: 'name', header: 'name'},
      {field: 'destination', header: 'destination'},
      {field: 'startDate', header: 'start date'},
      {field: 'endDate', header: 'end date'},
      {field: 'actions', header: 'actions'}
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



  onDelete(id: number) {
    // this.tripsFacade.deleteUser(id).subscribe();
  }

  onEdit(id: number) {
    // this.router.navigateByUrl('/admin/edit-user/' + id);
  }

  onDetailsClick(id: number) {
    this.router.navigate(['../', '../', 'trip-details', id]);
  }

  onViewGroup(id: number) {
    console.log('navigating');
    this.router.navigateByUrl('/trips/trip-details/' + id);
  }

  onJoinGroup(id: number) {
    this.tripsFacade.joinGroup(id);

  }

  onDeleteGroup(groupId: number) {

    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this trip?',
      accept: () => {
        this.tripsFacade.deleteGroup(groupId).subscribe(() => {
          this.trips = this.trips$.value.filter(t => t.id != groupId);
          this.trips$.next(this.trips);
        });
      }
    });


  }
}
