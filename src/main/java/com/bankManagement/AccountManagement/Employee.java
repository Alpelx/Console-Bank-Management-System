package com.bankManagement.AccountManagement;

/**
 * @Description This is the class that define an employee to make
 * easier managing his data and operations through the java program
 */

import com.bankManagement.Features.ConsoleFeatures;

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

    public Employee(int id, String idnp, String firstname, String lastname, LocalDate dateOfBirth, String functionAtWork, int workExperience) {
        this.id = id;
        this.idnp = idnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.functionAtWork = functionAtWork;
        this.workExperience = workExperience;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ConsoleFeatures.BLUE_BOLD + "\nMy Data: " +
                ConsoleFeatures.BLUE + "\nId: " + id + "\nIdnp: " +
                idnp + "\nFirstname: " + firstname + "\nLastname: " +
                lastname + "\nDate of birth: " +
                dateOfBirth.format(dateTimeFormatter) +
                "\nFunction at work: " + functionAtWork +
                "\nWork Experience: " + workExperience + "\n\n" +
                ConsoleFeatures.RESET;
    }
}
