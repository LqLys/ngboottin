import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Credentials} from "../../models";
import {environment} from "../../../environments/environment";
import {log} from "util";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  observe: 'response' as 'response'
};

const API_URL = environment.API_BASE_PATH;
const BASE_PATH = environment.BASE_PATH;

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private http: HttpClient) {
  }


  public getResponseHeaders(credentials: Credentials) {
    let loginUrl = API_URL + '/login';
    return this.http.post(loginUrl, credentials, httpOptions);
  }

  public logout() {
    const logoutUrl = BASE_PATH + '/logout';
    return this.http.post(logoutUrl, null, {responseType: 'text'});
  }

  public getRole(token: string): string {
    const parseJwt = () => {
      try {
        return JSON.parse(atob(token.split('.')[1]));
      } catch (e) {
        return null;
      }
    };
    if (parseJwt()) {
      const authorities: string[] = parseJwt().authorities;
      return authorities[0] ? authorities[0] : '';
    }
    return '';
  }

  getTokenUsername(token: string) {
    const parseJwt = () => {
      try {
        return JSON.parse(atob(token.split('.')[1]));
      } catch (e) {
        return null;
      }
    };
    if (parseJwt()) {
      const username: string[] = parseJwt().sub;
      return username ? username : '';
    }
    return '';
  }
}
