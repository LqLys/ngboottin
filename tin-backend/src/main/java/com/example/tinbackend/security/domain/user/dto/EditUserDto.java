package com.example.tinbackend.security.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {

    @NotNull
    private Long id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @Length(min = 3, message = "min username length is 3 chars")
    private String username;
    @Email(message = "incorrect email format")
    private String email;

    private String password;

}
