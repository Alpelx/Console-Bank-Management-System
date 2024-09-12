package com.bankManagement.AccountManagement.DAO_Models;

/**
 * @Description: This is the class that define user to make easier
 * managing his data and operations through the java program.
 */

import com.bankManagement.Features.Colorable;
import com.bankManagement.Features.DateFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    private int id;
    private String idnp;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private double balance;
    private boolean account;

    public User(int id, String idnp, String firstname, String lastname,
                LocalDate dateOfBirth, double balance,
                boolean account) {
        this.id = id;
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.account = account;
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
        return Colorable.BLUE_BOLD + "My data:"
                + Colorable.BLUE
                + "\nId: " + id
                + "\nIdnp: " + idnp
                + "\nFirstname: " + firstname
                + "\nLastname: " + lastname
                + "\nDate of birth: "
                + dateOfBirth.format(DateFormat.DATE_FORMAT)
                + "\nBalance: " + balance
                + "\nHas account: " + account + "\n\n" +
                Colorable.RESET;
    }
}
