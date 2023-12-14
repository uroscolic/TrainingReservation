package com.example.reservation.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserCreateDto {

    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate dateOfBirth;

    public UserCreateDto() {
    }
    public UserCreateDto(String firstName, String lastName, String username, String password, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }




}
