package com.bankManagement.AccountManagement.DAO_Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    private int id;
    private String idnp;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double balance;
    private boolean has_account;

    public User(int id, String idnp, String firstName, String lastName,
                LocalDate dateOfBirth, double balance,
                boolean has_account) {
        this.id = id;
        this.idnp = idnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.has_account = has_account;
    }
}
