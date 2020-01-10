import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, ValidationErrors, Validators} from '@angular/forms';
import {AdminUserFacade} from '../../../facades/admin-user.facade';
import {UserData} from '../../../../models';
import {PasswordValidator} from '../../../../shared/validators/password.validator';
import {Observable, of, timer} from 'rxjs';
import {map, switchMap} from 'rxjs/operators';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-admin-users-add',
  templateUrl: './admin-users-add.component.html',
  styleUrls: ['./admin-users-add.component.css']
})
export class AdminUsersAddComponent implements OnInit {
  registerForm = this.fb.group({
    name: ['', [Validators.required]],
    username: ['', [Validators.required, Validators.minLength(3)], this.validateUsernameTaken.bind(this)],
    password: ['', [Validators.required, PasswordValidator.strongPassword]],
    email: ['', [Validators.required, Validators.email]]
  });

  constructor(private fb: FormBuilder, private userFacade: AdminUserFacade, private messageService: MessageService) {
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.registerForm.value)
    if (this.registerForm.valid) {
      const userData: UserData = {name: this.registerForm.value.name,
                                  username: this.registerForm.value.username,
                                  password: this.registerForm.value.password,
                                  email: this.registerForm.value.email};
      this.userFacade.addUser(userData).subscribe(() => {
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'User added'});
        },
        error => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Error adding user'});
        });
    }
  }

  validateUsernameTaken(control: AbstractControl): Observable<ValidationErrors> {
    return timer(500).pipe(
      switchMap(() => {
        if (!control.value) {
          return of(null);
        }
        return this.userFacade.getUserByUsername(control.value).pipe(
          map(result => (result ? { usernameTaken: true } : null))
        );
      })
    );
  }
}
