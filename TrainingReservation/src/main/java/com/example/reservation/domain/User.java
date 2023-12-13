package com.example.reservation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@MappedSuperclass
@Entity
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
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    @ManyToOne(optional = false)
    private Role role;

    public User(Long id, String name, String surname, String username, String password, String email, LocalDate dateOfBirth, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public User() {
    }
}

