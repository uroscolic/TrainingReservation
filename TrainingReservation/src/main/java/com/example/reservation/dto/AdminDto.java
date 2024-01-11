package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto extends UserDto{

    public AdminDto() {
        super();
    }
    public AdminDto(String firstName, String lastName, String username, String email, boolean banned, String role) {
        super(firstName, lastName, username, email, banned, role);

    }
}
