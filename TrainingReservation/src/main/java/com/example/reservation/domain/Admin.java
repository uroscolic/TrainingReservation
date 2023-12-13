package com.example.reservation.domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(Long id, String name, String surname, String username, String password, String email, LocalDate dateOfBirth, Role role) {
        super(id, name, surname, username, password, email, dateOfBirth, role);
    }
}
