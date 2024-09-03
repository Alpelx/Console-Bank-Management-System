package com.bankManagement.AccountManagement;

import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeActions;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.Features.ConsoleTextColors;
import org.jetbrains.annotations.Nullable;

/**
 * @Description this is the class that performs the login process of
 * any type of account, regardless of whether it is an employee, admin
 * or a simple user. This class provide login process for each of 'em
 */

public abstract class Login {
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
        System.out.println(ConsoleTextColors.RED_BOLD
                + "Error [ wrong login or password ] \n\n"
                + ConsoleTextColors.RESET);
        return null;
    }

    public static @Nullable Employee logInAsAdmin(String login,
                                                  String password) {
        EmployeeAccountActions employeeAccountActions =
                new EmployeeAccountActions();
        EmployeeAccount employeeAccount = employeeAccountActions
                .getEmployeeAccount(login, password);
        if (employeeAccount == null) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ wrong login or password ] \n\n"
                    + ConsoleTextColors.RESET);
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
}
