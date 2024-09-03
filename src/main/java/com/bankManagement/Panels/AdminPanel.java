package com.bankManagement.Panels;

/**
 * @Description this is the class what define admin panel which is opened
 * when an admin is logged in. Here he can do any administrator actions.
 * Each action is defined by own methods with private access modifier for
 * being unable to be called by other classes.
 */

import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserActions;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.Removing;
import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.ConsoleTextColors;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AdminPanel extends Menu {
    private EmployeeAccountActions employeeAccountActions;
    private EmployeeActions employeeActions;
    private UserAccountActions userAccountActions;
    private UserActions userActions;

    public AdminPanel() {
        this.employeeAccountActions = new EmployeeAccountActions();
        this.employeeActions = new EmployeeActions();
        this.userAccountActions = new UserAccountActions();
        this.userActions = new UserActions();
    }

    @Override
    protected void showMenuList() {
        System.out.println(ConsoleTextColors.CYAN_BOLD
                + "\n\nAdmin Panel\n" + ConsoleTextColors.RESET
                + "[1] -> Display data about users\n"
                + "[2] -> Display data about employees\n"
                + "[3] -> Register an user\n"
                + "[4] -> Hire a new employee\n"
                + "[5] -> Remove an user\n"
                + "[6] -> Dismiss an employee\n"
                + "[7] -> Grant admin to an employee\n"
                + "[8] -> Revoke admin from an employee\n"
                + "[9] -> Display an user's transaction history\n"
                + "[10] -> Display whole transaction history"
                + ConsoleTextColors.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                displayUsers();
                break;
            case 2:
                displayEmployees();
                break;
            case 3:
                addUser();
                break;
            case 4:
                hireEmployee();
                break;
            case 5:
                removeUser();
                break;
            case 6:
                removeEmployee();
                break;
            case 0:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "You have closed the admin panel"
                        + ConsoleTextColors.RESET + "\n\n");
                return false;
            case -1:
                break;
            default:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "Error [ Wrong input ]"
                        + ConsoleTextColors.RESET);
                System.out.println();
                break;
        }
        return true;
    }

    private void displayUsers() {
        List<User> users = userActions.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void displayEmployees() {
        List<Employee> employees = employeeActions.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void addUser() {
        User user = createUser();
        userActions.addUser(user);
    }

    private User createUser() {
        String idnp = readIdnp();
        String firstname = ConsoleReading.readString("Enter first name: ");
        String lastname = ConsoleReading.readString("Enter last name: ");
        LocalDate dateOfBirth = ConsoleReading.readLocalDate();
        double balance = ConsoleReading.readInt("Enter balance: ");

        return new User(idnp, firstname, lastname, dateOfBirth, balance);
    }

    private void hireEmployee() {
        Employee employee = createEmployee();
        employeeActions.addEmployee(employee);
    }

    private Employee createEmployee() {
        String idnp = readIdnp();
        String firstname = ConsoleReading.readString("Enter firstname: ");
        String lastname = ConsoleReading.readString("Enter lastname: ");
        LocalDate dateOfBirth = ConsoleReading.readLocalDate();
        String functionAtWork = ConsoleReading
                .readString("Enter function at work (use '\\'' "
                        + "instead of space): ");
        int workExperience = ConsoleReading
                .readInt("Enter work experience: ");

        return new Employee(idnp, firstname, lastname, dateOfBirth,
                functionAtWork.replace("\\", " "),
                workExperience);
    }

    private @NotNull String readIdnp() {
        String idnp;
        do {
            idnp = ConsoleReading.readString("Enter idnp: ");
        } while (!isValidIdnp(idnp));
        return idnp;
    }

    private boolean isValidIdnp(String idnp) {
        return idnp.length() == 13 && idnp.matches("[0-9]{13}");
    }

    private void removeUser() {
        String firstname = ConsoleReading.readString("Enter first name: ");
        String lastname = ConsoleReading.readString("Enter last name: ");
        User user = userActions.getUser(firstname, lastname);
        if (user != null) {
            Removing.removeUser(user);
        } else {
            displayErrorMessage();
        }
    }

    private void removeEmployee() {
        String firstname = ConsoleReading.readString("Enter first name: ");
        String lastname = ConsoleReading.readString("Enter last name: ");
        Employee employee = employeeActions.getEmployee(firstname, lastname);
        if (employee != null) {
            Removing.removeEmployee(employee);
        } else {
            displayErrorMessage();
        }
    }

    private static void displayErrorMessage() {
        System.out.println(ConsoleTextColors.RED_BOLD
                + "Error [ wrong login or password ] \n\n"
                + ConsoleTextColors.RESET);
    }
}
