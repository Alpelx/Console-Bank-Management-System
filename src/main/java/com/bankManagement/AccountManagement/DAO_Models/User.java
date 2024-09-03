package com.bankManagement.AccountManagement.DAO_Models;

import com.bankManagement.Features.ConsoleTextColors;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class User {
    private int id;
    private String idnp;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private double balance;
    private boolean hasAccount;

    public User(int id, String idnp, String firstname, String lastname,
                LocalDate dateOfBirth, double balance,
                boolean hasAccount) {
        this.id = id;
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.hasAccount = hasAccount;
    }

    public User(String idnp, String firstname, String lastname,
                LocalDate dateOfBirth, double balance) {
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ConsoleTextColors.BLUE_BOLD + "My data:"
                + ConsoleTextColors.BLUE
                + "\nId: " + id
                + "\nIdnp: " + idnp
                + "\nFirstname: " + firstname
                + "\nLastname: " + lastname
                + "\nDate of birth: " + dateOfBirth.format(dateTimeFormatter)
                + "\nBalance: " + balance
                + "\nHas account: " + hasAccount + "\n\n" +
                ConsoleTextColors.RESET;
    }
}
