package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private boolean banned;
    private String role;

    public UserDto() {
    }
    public UserDto(String firstName, String lastName, String username, String email, boolean banned, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.banned = banned;
        this.role = role;

    }
}
