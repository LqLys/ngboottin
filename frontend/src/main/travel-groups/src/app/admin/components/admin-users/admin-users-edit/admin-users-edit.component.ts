import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, ValidationErrors, Validators} from '@angular/forms';
import {AdminUserFacade} from '../../../facades/admin-user.facade';
import {ChangePasswordDto, User} from '../../../../models';
import {ActivatedRoute} from '@angular/router';
import {PasswordValidator} from '../../../../shared/validators/password.validator';
import {Observable, of, timer} from 'rxjs';
import {map, switchMap} from 'rxjs/operators';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-admin-users-edit',
  templateUrl: './admin-users-edit.component.html',
  styleUrls: ['./admin-users-edit.component.css']
})
export class AdminUsersEditComponent implements OnInit {

  userForm = this.fb.group({
    name: ['', [Validators.required]],
    username: ['', [Validators.required, Validators.minLength(3)], this.validateUsernameTaken.bind(this)],
    email: ['', [Validators.required, Validators.email]]
  });

  passwordForm = this.fb.group({
    oldPassword: [''],
    newPassword: ['', [Validators.required, PasswordValidator.strongPassword]]
  });
  currentUsername;

  constructor(private fb: FormBuilder, private userFacade: AdminUserFacade,
              private route: ActivatedRoute, private messageService: MessageService) {
  }

  ngOnInit() {
    const id = this.route.snapshot.params.id;
    console.log(id);
    this.userFacade.getUserById(id).subscribe(user => {
      this.userForm.setValue({name: user.name, username: user.username, email: user.email});
      this.currentUsername = this.userForm.value.username;
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      const user: User = {
        id: this.route.snapshot.params.id,
        name: this.userForm.value.name,
        username: this.userForm.value.username,
        email: this.userForm.value.email
      };
      this.userFacade.updateUser(user).subscribe(() => {
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'User edited'});
      },
        error => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Error editing user'});
        });
    }
  }

  onPasswordChange() {
    if (this.passwordForm.valid) {
      const changePassword: ChangePasswordDto = {
        username: this.userForm.value.username,
        oldPassword: this.passwordForm.value.oldPassword,
        newPassword: this.passwordForm.value.newPassword
      };
      console.log(changePassword);
      this.userFacade.changePassword(changePassword).subscribe(() => {
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Password changed'});
        },
        error => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Error changing password'});
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
          map(result => (result && control.value !== this.currentUsername ? {usernameTaken: true} : null))
        );
      })
    );
  }

}
