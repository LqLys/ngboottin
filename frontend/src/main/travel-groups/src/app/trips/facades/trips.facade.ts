import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {AddTripWithUsers, ChatMessage, EditGroup, SendMessage, Trip, User, UserTrip} from '../../models';
import {tap} from 'rxjs/operators';
import {TripsService} from '../services/trips.service';
import {AuthService} from '../../auth/services/auth.service';
import {Router} from '@angular/router';
import {TripChatService} from '../services/trip-chat.service';

@Injectable({providedIn: 'root'})
export class TripsFacade {

  trips$: BehaviorSubject<Trip[]> = new BehaviorSubject<Trip[]>([]);
  users$: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);
  userTrip$: BehaviorSubject<UserTrip[]> = new BehaviorSubject<UserTrip[]>([]);
  messages$: BehaviorSubject<ChatMessage[]> = new BehaviorSubject<ChatMessage[]>([]);


  constructor(private tripService: TripsService, private authService: AuthService, private router: Router, private chatService: TripChatService) {
  }

  loadTripsByUserId(userId: number) {
    return this.tripService.loadAllTrips().pipe(
      tap(data => this.trips$.next(data))
    );
  }

  loadTripById(tripId: number): Observable<Trip> {
    return this.tripService.loadTripById(tripId);
  }

  addTripWithUsers(tripData: AddTripWithUsers): Observable<void> {
    return this.tripService.addTripWithUsers(tripData);
  }

  loadAllTrips() {
    return this.tripService.loadAllTrips().pipe(
      tap(data => this.trips$.next(data))
    );
  }

  joinGroup(id: number) {
    const username = this.authService.getAuthenticatedUsername();
    this.tripService.isMemberAlready(id, username).subscribe(isMember => {
      if (isMember) {
        this.router.navigateByUrl('/trips/trip-details/' + id);
      } else {
        this.tripService.joinGroup(id, username).subscribe(() => this.router.navigateByUrl('/trips/trip-details/' + id));
      }
    });
  }

  loadUsersByGroupId(groupId: number) {
    return this.tripService.loadUsersByGroupId(groupId).pipe(
      tap(users => {
          console.log(users);
          this.users$.next(users);
        }
      )
    );
  }

  loadUserTrips(groupId: number) {
    return this.tripService.loadUserTrips(groupId).pipe(
      tap(users => {
          console.log(users);
          this.userTrip$.next(users);
        }
      )
    );
  }

  updateParticipation(userTrip: UserTrip) {
    this.tripService.updateParticipation(userTrip).subscribe();

  }

  editGroup(editGroup: EditGroup) {
    return this.tripService.updateGroup(editGroup);
  }

  removeMember(id: number) {
    return this.tripService.removeMember(id);

  }

  deleteGroup(groupId: number) {
    return this.tripService.deleteGroup(groupId);
  }

  settle() {
    return this.tripService.settle();
  }

  settleByGroupId(groupId: any) {
    return this.tripService.settleByGroupId(groupId);

  }

  loadChatMessagesByGroupId(groupid: number) {
    return this.chatService.getMessagesByGroupId(groupid).pipe(
      tap(messages => this.messages$.next(messages))
    );
  }

  sendChatMessage(message: SendMessage) {
    return this.chatService.createChatMessage(message);
  }
}
