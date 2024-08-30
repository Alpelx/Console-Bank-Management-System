package com.bankManagement.AccountManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private int id;
    private String idnp;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String functionAtWork;
    private int workExperience;
    private boolean hasAccount;

    public Employee(int id, String idnp, String firstname, String lastname, LocalDate dateOfBirth, String functionAtWork, int workExperience) {
        this.id = id;
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.functionAtWork = functionAtWork;
        this.workExperience = workExperience;
    }

    public Employee(int id, String idnp, String firstname, String lastname, LocalDate dateOfBirth, String functionAtWork, int workExperience, boolean hasAccount) {
        this.id = id;
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.functionAtWork = functionAtWork;
        this.workExperience = workExperience;
        this.hasAccount = hasAccount;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Id: " + id + "\nIdnp: " + idnp + "\nFirstname: " +
                firstname + "\nLastname: " + lastname +
                "\nDate of birth: " +
                dateOfBirth.format(dateTimeFormatter) +
                "\nFunction at work: " + functionAtWork +
                "\nWork Experience: " + workExperience;
    }
}
