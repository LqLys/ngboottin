import {Injectable} from '@angular/core';
import {AdminUserService} from '../services/admin-user.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {ChangePasswordDto, User, UserData} from '../../models';
import {debounceTime, delay, tap} from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class AdminUserFacade {

  users$: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);

  constructor(private adminUserService: AdminUserService) {
  }

  loadUsers(): Observable<User[]> {
    return this.adminUserService.loadUsers().pipe(
      tap(data => this.users$.next(data))
    );
  }

  deleteUser(id: number) {
    return this.adminUserService.deleteUser(id).pipe(
      tap(() => this.loadUsers().subscribe()),
    );
  }

  addUser(userData: UserData) {
    return this.adminUserService.addUser(userData).pipe(
      tap(() => this.loadUsers().subscribe())
    );
  }

  getUserById(id: number) {
    return this.adminUserService.getUserById(id);
  }

  updateUser(user: User) {
    return this.adminUserService.updateUser(user).pipe(
      tap(() => this.loadUsers().subscribe())
    );

  }

  getUserByUsername(value: any): Observable<User[]> {
    return this.adminUserService.getUserByUsername(value);
  }

  register(userData: UserData) {
    return this.adminUserService.addUser(userData);
  }

  changePassword(changePassword: ChangePasswordDto) {
    return this.adminUserService.changePassword(changePassword);

  }
}
