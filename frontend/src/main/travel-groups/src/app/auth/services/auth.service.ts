import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {TokenService} from "./token.service";
import {Credentials} from "../../models";
import {HttpResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  redirectToUrl = '/';


  constructor( private router: Router, private tokenService: TokenService) { }

  public login(credentials: Credentials): void {
    this.tokenService.getResponseHeaders(credentials)
      .subscribe((res: HttpResponse<any>) => {
        this.saveToken(res.headers.get('Authorization').replace('Bearer', ''));
        this.router.navigate([this.redirectToUrl]);
      });
  }

  public getToken(): string {
    return localStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
  }

  public logout(): void {
    localStorage.removeItem(AuthService.TOKEN_STORAGE_KEY);
    this.router.navigateByUrl('/');
  }

  public isLoggedIn(): boolean {
    return !!this.getToken();
  }

  private saveToken(token: string) {
    localStorage.setItem(AuthService.TOKEN_STORAGE_KEY, token);
  }

  public isAdmin(): boolean{
    return this.tokenService.getRole(this.getToken()) === 'ADMIN';
  }


  getAuthenticatedUsername() {
    return this.tokenService.getTokenUsername(this.getToken());
  }
}
