package com.example.tinbackend.security.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.annotation.RegEx;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {

    @NotBlank(message = "name cannot be empty")
    private String name;
    @Length(min = 3, message = "min username length is 3 chars")
    private String username;
    @Email(message = "incorrect email format")
    private String email;
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&|*])[A-Za-z\\d$@$!%*?&].{8,}",
    message = "incorrect password format")
    private String password;
}
