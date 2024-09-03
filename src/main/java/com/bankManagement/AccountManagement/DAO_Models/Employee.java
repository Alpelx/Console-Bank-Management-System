package com.bankManagement.AccountManagement.DAO_Models;

/**
 * @Description This is the class that define an employee to make
 * easier managing his data and operations through the java program
 */

import com.bankManagement.Features.ConsoleTextColors;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Employee {
    private int id;
    private String idnp;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String functionAtWork;
    private int workExperience;
    private boolean has_account;

    public Employee(int id, String idnp, String firstname, String lastname,
                    LocalDate dateOfBirth, String functionAtWork,
                    int workExperience, boolean has_account) {
        this.id = id;
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.functionAtWork = functionAtWork;
        this.workExperience = workExperience;
        this.has_account = has_account;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ConsoleTextColors.BLUE_BOLD + "\nMy Data: "
                + ConsoleTextColors.BLUE
                + "\nId: " + id
                + "\nIdnp: " + idnp
                + "\nFirstname: " + firstname
                + "\nLastname: " + lastname +
                "\nDate of birth: " + dateOfBirth.format(dateTimeFormatter)
                + "\nFunction at work: " + functionAtWork
                + "\nWork experience: " + workExperience
                + "\nHas account: " + has_account
                + ConsoleTextColors.RESET;
    }
}
