package com.example.reservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateDto {
    @NotBlank
    private String oldUsername;
    @NotNull
    private Long id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    @Size(min = 8)
    private String password;
    private boolean banned;
}
