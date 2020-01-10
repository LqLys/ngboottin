import {FormControl} from '@angular/forms';

export interface ValidationResult {
  [key: string]: boolean;
}

export class PasswordValidator {

  public static strongPassword(control: FormControl): ValidationResult {
    const hasNumber = /\d/.test(control.value);
    const hasUpper = /[A-Z]/.test(control.value);
    const hasLower = /[a-z]/.test(control.value);
    const isLong = control.value.length > 7;
    const hasSpecial = /[!@#$%^&*|]/.test(control.value);
    const valid = hasNumber && hasLower && hasUpper && isLong && hasSpecial;

    if (!valid) {
      return {strong: true};
    }
    return null;
  }
}
