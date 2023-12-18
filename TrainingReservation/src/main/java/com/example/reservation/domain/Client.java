package com.example.reservation.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("Client")
public class Client extends User{

    private Long cardNumber;
    private int numberOfTrainings;
    private boolean isBlocked;
    private boolean isActivated;
    public Client() {
        super();
    }

    public Client(Long id, String name, String surname, String username, String password, String email, LocalDate dateOfBirth, Role role, Long cardNumber, int numberOfTrainings) {
        super(id, name, surname, username, password, email, dateOfBirth, role);
        this.cardNumber = cardNumber;
        this.numberOfTrainings = numberOfTrainings;
        this.isBlocked = false;
        this.isActivated = false;
    }
}
