package com.example.reservation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@Entity
@Table(name = "useraccount", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (discriminatorType = DiscriminatorType.STRING,
name = "UserType")
public class User {

    /*
    Tipovi korisnika - U sistemu je potrebno da postoji više tipova korisnika sa
    različitim skupovima podataka i različitim privilegijama. Podaci koje bi svaki
    tip korisnika trebalo da ima su: korisničko ime (username), lozinku
    (password), imejl, datum rođenja, ime i prezime. Tri tipa korisinka koje
    sistem podržava su:

    admin (nema ništa od dodatnih atributa, ima sve
    privilegije i ručno se unosi u bazu),

    klijent (jedinstveni broj članske karte,
    broj zakazanih treninga),

    menadžer fiskulturne sale (naziv fiskulturne sale u
    kojoj radi i datum zapošljavanja).
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    @ManyToOne(optional = false)
    private Role role;
    private String activationCode;


    public User(Long id, String firstName, String lastName, String username, String password, String email, LocalDate dateOfBirth, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public User() {
    }
}

