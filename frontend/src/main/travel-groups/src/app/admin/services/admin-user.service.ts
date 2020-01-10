import {Injectable} from '@angular/core';
import {ChangePasswordDto, User, UserData} from '../../models';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminUserService {
  private apiBase = environment.API_BASE_PATH;


  constructor(private http: HttpClient) {
  }

  loadUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiBase + '/user');
  }

  addUser(userData: UserData): Observable<User> {
    return this.http.post<User>(this.apiBase + '/user', userData);
  }

  deleteUser(id: number) {
    return this.http.delete(this.apiBase + '/user/' + id);
  }

  getUserById(userId: number) {
    return this.http.get<User>(this.apiBase + '/user/' + userId);
  }

  updateUser(user: User) {
    return this.http.put(this.apiBase + '/user/', user);
  }

  getUserByUsername(username: string): Observable<User[]>{
    return this.http.get<User[]>(this.apiBase + '/validate/username-exists',
      {
        params: {
          username
        }
      });
  }

  changePassword(changePassword: ChangePasswordDto) {
    return this.http.put<any>(this.apiBase + '/user/change-password', changePassword);
  }
}
