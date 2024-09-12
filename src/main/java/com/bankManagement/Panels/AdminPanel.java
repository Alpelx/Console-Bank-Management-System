package com.bankManagement.Panels;

/**
 * @Description: this is the class what define admin panel which is
 * opened when an admin is logged in. Here he can do any administrator
 * actions. Each action is defined by own method/s with private access
 * modifier for being unable to be called by other classes. The class
 * does not interact directly with mySql, instead it works with DAO
 * models and implementations.
 */

import com.bankManagement.AccountManagement.DAO_Implimentations.*;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.Removing;
import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.Colorable;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AdminPanel extends Menu {
    private EmployeeAccountActions employeeAccountActions;
    private EmployeeActions employeeActions;
    private UserActions userActions;
    private TransactionActions transactionActions;

    public AdminPanel() {
        this.employeeAccountActions = new EmployeeAccountActions();
        this.employeeActions = new EmployeeActions();
        this.userActions = new UserActions();
        transactionActions = new TransactionActions();
    }

    @Override
    protected void showMenuList() {
        System.out.println(Colorable.CYAN_BOLD
                + "\n\nAdmin Panel\n" + Colorable.RESET
                + "[1] -> Display data about users\n"
                + "[2] -> Display data about employees\n"
                + "[3] -> Register an user\n"
                + "[4] -> Hire a new employee\n"
                + "[5] -> Remove an user\n"
                + "[6] -> Dismiss an employee\n"
                + "[7] -> Grant admin to an employee\n"
                + "[8] -> Revoke admin from an employee\n"
                + "[9] -> Display whole transaction history"
                + "[10] -> Display an user's transaction history\n"
                + Colorable.RESET);
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
            case 7:
                grantAdmin();
                break;
            case 8:
                revokeAdmin();
                break;
            case 9:
                displayAllTransactions();
                break;
            case 10:
                displayUserTransactions();
                break;
            case 0:
                System.out.println(Colorable.RED_BOLD
                        + "You have closed the admin panel"
                        + Colorable.RESET + "\n\n");
                return false;
            case -1:
                break;
            default:
                System.out.println(Colorable.RED_BOLD
                        + "Error [ Wrong input ]"
                        + Colorable.RESET);
                System.out.println();
                break;
        }
        return true;
    }

    private void displayUsers() {
        List<User> users = userActions.getAllUsers();
        users.forEach(System.out::println);
        if (users.isEmpty()) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ users not found ]");
        }
    }

    private void displayEmployees() {
        List<Employee> employees = employeeActions.getAllEmployees();
        employees.forEach(System.out::println);
        if (employees.isEmpty()) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ employees not found ]");
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
                .readString("Enter function at work (use '\\'' instead "
                        + "of space): ").replace("\\", " ");
        int workExperience = ConsoleReading
                .readInt("Enter work experience: ");
        return new Employee(idnp, firstname, lastname, dateOfBirth,
                functionAtWork, workExperience);
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

    private void grantAdmin() {
        Employee employee = employeeActions.getEmployee(
                ConsoleReading.readString("Enter first name: "),
                ConsoleReading.readString("Enter last name: "));
        if (employee == null) {
            displayErrorMessage();
        } else {
            EmployeeAccount employeeAccount = employeeAccountActions
                    .getEmployeeAccount(employee.getId());
            if (employeeAccount == null) {
                System.out.println(Colorable.RED_BOLD
                        + "Employee does not have account"
                        + Colorable.RESET);
            } else {
                employeeAccount.setRole("administrator");
                employeeAccountActions.updateAccount(employeeAccount);
            }
        }
    }

    private void revokeAdmin() {
        Employee employee = employeeActions.getEmployee(
                ConsoleReading.readString("Enter first name: "),
                ConsoleReading.readString("Enter last name: "));
        if (employee == null) {
            displayErrorMessage();
        } else {
            EmployeeAccount employeeAccount = employeeAccountActions
                    .getEmployeeAccount(employee.getId());
            if (employeeAccount == null) {
                System.out.println(Colorable.RED_BOLD
                        + "Employee does not have account"
                        + Colorable.RESET);
            } else {
                employeeAccount.setRole("regular");
                employeeAccountActions.updateAccount(employeeAccount);
            }
        }
    }

    private void displayAllTransactions() {
        List<Transaction> transactions = transactionActions
                .getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ no transactions found ]"
                    + Colorable.RESET);
            return;
        }
        transactions.forEach(System.out::println);
    }

    private void displayUserTransactions() {
        String firstname = ConsoleReading.readString("Enter first name: ");
        String lastname = ConsoleReading.readString("Enter last name: ");
        User user = userActions.getUser(firstname, lastname);
        if (user == null) {
            displayErrorMessage();
        } else {
            List<Transaction> transactions = transactionActions
                    .getTransactionsByAccountId(user.getId());
            if (transactions.isEmpty()) {
                System.out.println(Colorable.RED_BOLD
                        + "Error [ no transactions found ]"
                        + Colorable.RESET);
                return;
            }
            transactions.forEach(System.out::println);
        }
    }

    private void displayErrorMessage() {
        System.out.println(Colorable.RED_BOLD
                + "Error [ wrong login or password ] \n\n"
                + Colorable.RESET);
    }
}
