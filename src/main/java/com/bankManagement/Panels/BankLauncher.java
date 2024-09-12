package com.bankManagement.Panels;

/**
 * @Description: This is class which is called on the running of
 * application.Here are two functionalities: login (user, employee,
 * admin) and registration (user, employee). There are no allowed
 * registering as admin, because admin privileges can be granted only
 * by other admins. The class does not interact directly with mySql,
 * instead it works with DAO models and implementations.
 */


import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.Login;
import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.Colorable;

public class BankLauncher extends Menu {
    @Override
    protected void showMenuList() {
        System.out.println(Colorable.PURPLE_BOLD
                + "WELCOME TO OUR BANK!\n" + Colorable.PURPLE
                + "(1) -> Log in as an employee\n"
                + "(2) -> Log in as an admin\n"
                + "(3) -> Log in as an user\n"
                + "(4) -> Register an account\n"
                + "(0) -> Exit"
                + Colorable.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                logInAsEmployee();
                break;
            case 2:
                logInAsAdmin();
                break;
            case 3:
                logInAsUser();
                break;
            case 4:
                RegisterPanel registerPanel = new RegisterPanel();
                registerPanel.run();
                break;
            case 0:
                System.out.println(Colorable.RED_BOLD
                        + "You have closed the program"
                        + Colorable.RESET);
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

    private void logInAsUser() {
        User user = Login.logInAsUser(
                ConsoleReading.readString("Enter login: "),
                ConsoleReading.readString("Enter password: "));
        if (user != null) {
            UserPanel userPanel = new UserPanel(user);
            userPanel.run();
        }
    }

    private void logInAsEmployee() {
        Employee employee = Login.logInAsEmployee(
                ConsoleReading.readString("Enter login: "),
                ConsoleReading.readString("Enter password: "));
        if (employee != null) {
            EmployeePanel employeePanel = new EmployeePanel(employee);
            employeePanel.run();
        }
    }

    private void logInAsAdmin() {
        Employee admin = Login.logInAsAdmin(
                ConsoleReading.readString("Enter login: "),
                ConsoleReading.readString("Enter password: "));
        if (admin != null) {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.run();
        }
    }
}
