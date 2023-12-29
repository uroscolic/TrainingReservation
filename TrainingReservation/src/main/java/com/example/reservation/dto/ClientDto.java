package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends UserDto{

    private int numberOfTrainings;
    public ClientDto() {
        super();
    }

    public ClientDto(String firstName, String lastName, String username, String email) {
        super(firstName, lastName, username, email);
    }
}
