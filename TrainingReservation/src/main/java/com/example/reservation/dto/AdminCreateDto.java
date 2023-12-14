package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminCreateDto extends UserCreateDto{
    public AdminCreateDto() {
        super();
    }
    public AdminCreateDto(String firstName, String lastName, String username, String password, String email, LocalDate dateOfBirth) {
        super(firstName, lastName, username, password, email,dateOfBirth);
    }
}
//   TODO verovatno nam ni ne treba adminCreateDto