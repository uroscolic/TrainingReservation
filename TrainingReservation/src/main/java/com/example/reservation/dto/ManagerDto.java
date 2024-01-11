package com.example.reservation.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDto extends UserDto{

    private String nameOfGym;

    public ManagerDto() {
        super();
    }
    public ManagerDto(String firstName, String lastName, String username, String email, boolean banned, String role, String nameOfGym) {
        super(firstName, lastName, username, email, banned, role);
        this.nameOfGym = nameOfGym;
    }
}
