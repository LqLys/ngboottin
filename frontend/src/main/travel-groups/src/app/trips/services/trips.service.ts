import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {AddTripWithUsers, EditGroup, JoingroupDto, Trip, User, UserTrip} from '../../models';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TripsService {
  private apiBase = environment.API_BASE_PATH;


  constructor(private http: HttpClient) {
  }

  loadAllTrips() {
    return this.http.get<Trip[]>(this.apiBase + '/travel-group');
  }

  loadTripById(tripId: number): Observable<Trip> {
    // const trip = this.trips.find(id => +id === +tripId);
    // return new Observable<Trip>(obs => {
    //   obs.next(trip);
    // });
    return this.http.get<Trip>(this.apiBase + '/travel-group/' + tripId);

  }

  addTripWithUsers(tripData: AddTripWithUsers) {
    return this.http.post<any>(this.apiBase + '/travel-group/add-with-users', tripData);
  }

  joinGroup(groupId: number, username: string | string[]) {
    const joingroupdto: JoingroupDto = {groupId: groupId, username: username};
    return this.http.post<any>(this.apiBase + '/travel-group/join-group', joingroupdto);
  }

  loadUsersByGroupId(groupId: number): Observable<User[]> {
    return this.http.get<User[]>(this.apiBase + '/travel-group/users/' + groupId);
  }

  loadUserTrips(groupId: number): Observable<UserTrip[]> {
    return this.http.get<UserTrip[]>(this.apiBase + '/travel-group/users-participation/' + groupId);
  }


  updateParticipation(userTrip: UserTrip): Observable<any> {
    return this.http.put(this.apiBase + '/travel-group/participation', userTrip);

  }

  isMemberAlready(id: number, username: string[] | string): Observable<boolean> {
    return this.http.get<boolean>(this.apiBase + `/travel-group/${id}/member/${username}`);
  }

  updateGroup(editGroup: EditGroup) {
    return this.http.put<any>(this.apiBase + '/travel-group', editGroup);
  }

  removeMember(id: number) {
    return this.http.put<any>(this.apiBase + '/travel-group/remove-member/', {id});
  }

  deleteGroup(groupId: number) {
    return this.http.delete<any>(this.apiBase + '/travel-group/' + groupId);

  }

  settle() {
    return this.http.post<any>(this.apiBase + '/travel-group/settle', null);
  }

  settleByGroupId(groupId: any) {
    return this.http.post<any>(this.apiBase + '/travel-group/settle/' + groupId, null);
  }
}
