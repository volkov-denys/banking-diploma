package com.diploma.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String documentNumber;
    private LocalDate birthDate;
    private LocalDate createdAt;

    @Deprecated
    protected User() {}

    public User(String login, String password, String name, String surname, String documentNumber, LocalDate birthDate, LocalDate createdAt) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
