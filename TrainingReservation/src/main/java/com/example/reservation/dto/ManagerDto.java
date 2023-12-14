package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDto extends UserDto{
    private String nameOfGym;

    public ManagerDto(String nameOfGym, String firstName, String lastName, String username, String password, String email) {
        super(firstName, lastName, username, password, email);
        this.nameOfGym = nameOfGym;
    }
}
