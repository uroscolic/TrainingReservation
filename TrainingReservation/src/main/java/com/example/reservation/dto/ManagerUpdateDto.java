package com.example.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerUpdateDto {

    private String oldUsername;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean blocked;
}
