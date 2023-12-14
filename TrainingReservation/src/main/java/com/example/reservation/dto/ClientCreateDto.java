package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientCreateDto extends UserCreateDto{
    public ClientCreateDto() {
        super();
    }
    public ClientCreateDto(String firstName, String lastName, String username, String password, String email, LocalDate dateOfBirth) {
        super(firstName, lastName, username, password, email,dateOfBirth);
    }
}
