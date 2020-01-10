import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {AdminUserFacade} from '../../../admin/facades/admin-user.facade';
import {Credentials, UserData} from "../../../models";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private authService: AuthService) {
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.loginForm.value);
    if (this.loginForm.valid) {
      const credentials: Credentials = {
        username: this.loginForm.value.username,
        password: this.loginForm.value.password
      };
      this.login(credentials);
    }
  }

  login(credentials: Credentials): void {
    this.authService.login(credentials);
  }


  form() {
    console.log(this.loginForm);
  }
}
