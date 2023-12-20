package com.example.reservation.dto;

public class ClientDto extends UserDto{

    public ClientDto() {
        super();
    }

    public ClientDto(String firstName, String lastName, String username, String email) {
        super(firstName, lastName, username, email);
    }
}
