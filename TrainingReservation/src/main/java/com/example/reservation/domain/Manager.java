package com.example.reservation.domain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("Manager")
public class Manager extends User{

    private String nameOfGym;
    private LocalDate dateOfEmployment;
    private boolean isBlocked;
    private boolean isActivated;

    public Manager() {
        super();
    }
    public Manager(Long id, String name, String surname, String username, String password, String email, LocalDate dateOfBirth, Role role, String nameOfGym, LocalDate dateOfEmployment) {
        super(id, name, surname, username, password, email, dateOfBirth, role);
        this.nameOfGym = nameOfGym;
        this.dateOfEmployment = dateOfEmployment;
        this.isBlocked = false;
        this.isActivated = false;
    }
}
