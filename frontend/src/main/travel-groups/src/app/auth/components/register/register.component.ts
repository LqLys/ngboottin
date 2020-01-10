import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, ValidationErrors, Validators} from '@angular/forms';
import {AdminUserFacade} from '../../../admin/facades/admin-user.facade';
import {UserData} from '../../../models';
import {map, switchMap} from 'rxjs/operators';
import {Observable, of, timer} from 'rxjs';
import {PasswordValidator} from '../../../shared/validators/password.validator';
import {MessageService} from 'primeng/api';
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  // At least 8 characters in length
  // Lowercase letters
  // Uppercase letters
  // Numbers
  // Special characters
  registerForm = this.fb.group({
    name: ['', [Validators.required]],
    username: ['', [Validators.required, Validators.minLength(3)], this.validateUsernameTaken.bind(this)],
    password: ['', [Validators.required, PasswordValidator.strongPassword]],
    email: ['', [Validators.required, Validators.email]]
  });

  constructor(private fb: FormBuilder, private userFacade: AdminUserFacade, private messageService: MessageService,
              private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.registerForm.value);
    if (this.registerForm.valid) {
      const userData: UserData = {name: this.registerForm.value.name,
        username: this.registerForm.value.username,
        password: this.registerForm.value.password,
        email: this.registerForm.value.email};
      this.userFacade.register(userData).subscribe(() => {
        this.router.navigateByUrl('/login');
      },
        () => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Error creating account'});
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

  form() {
    console.log(this.registerForm);
  }
}
