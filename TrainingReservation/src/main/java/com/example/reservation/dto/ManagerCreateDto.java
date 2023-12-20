package com.example.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ManagerCreateDto extends UserCreateDto{

    @NotBlank
    private String nameOfGym;
    @NotBlank
    private LocalDate dateOfEmployment;

    public ManagerCreateDto() {
        super();
    }
    public ManagerCreateDto(String nameOfGym,LocalDate dateOfEmployment , String firstName, String lastName, String username, String password, String email, LocalDate dateOfBirth) {
        super(firstName, lastName, username, password, email,dateOfBirth);
        this.nameOfGym = nameOfGym;
        this.dateOfEmployment = dateOfEmployment;
    }
}
