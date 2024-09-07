package com.bankManagement.AccountManagement;

import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserActions;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.DAO_Models.UserAccount;
import com.bankManagement.Features.ConsoleTextColors;
import org.jetbrains.annotations.Nullable;

/**
 * @Description: this is the class that performs the login process of
 * any type of account, regardless of whether it is an employee, admin
 * or a simple user. This class provide login process for each of them.
 * The class does not interact directly with mySql, instead it works with
 * DAO models and implementations
 */

public abstract class Login {
    public static @Nullable User logInAsUser(String login, String password) {
        UserAccountActions userAccountActions = new UserAccountActions();
        UserAccount userAccount = userAccountActions.getUserAccount(login,
                password);
        if (userAccount != null) {
            UserActions userActions = new UserActions();
            return userActions.getUser(userAccount.getUserId());
        }
        displayErrorMessage();
        return null;
    }

    public static @Nullable Employee logInAsEmployee(String login,
                                                     String password) {
        EmployeeAccountActions employeeAccountActions =
                new EmployeeAccountActions();
        EmployeeAccount employeeAccount = employeeAccountActions
                .getEmployeeAccount(login, password);
        if (employeeAccount != null) {
            EmployeeActions employeeActions = new EmployeeActions();
            return employeeActions.getEmployeeById(employeeAccount
                    .getEmployeeId());
        }
        displayErrorMessage();
        return null;
    }

    public static @Nullable Employee logInAsAdmin(String login,
                                                  String password) {
        EmployeeAccountActions employeeAccountActions =
                new EmployeeAccountActions();
        EmployeeAccount employeeAccount = employeeAccountActions
                .getEmployeeAccount(login, password);
        if (employeeAccount == null) {
            displayErrorMessage();
        } else if (!employeeAccount.getRole().equals("administrator")) {
            System.out.println(ConsoleTextColors.RED_BOLD
            + "Error [ permission denied ] \n\n"
            + ConsoleTextColors.RESET);
            return null;
        } else {
            EmployeeActions employeeActions = new EmployeeActions();
            return employeeActions.getEmployeeById(employeeAccount
                    .getEmployeeId());
        }
        return null;
    }

    private static void displayErrorMessage() {
        System.out.println(ConsoleTextColors.RED_BOLD
                + "Error [ wrong login or password ] \n\n"
                + ConsoleTextColors.RESET);
    }
}
