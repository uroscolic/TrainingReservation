package com.example.reservation.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "First name should not be blank")
    private String firstName;
    @NotBlank(message = "Last name should not be blank")
    private String lastName;
    @NotBlank(message = "Username should not be blank")
    private String username;
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;
    private LocalDate dateOfBirth;


}
