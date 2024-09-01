package com.bankManagement.Panels;

import com.bankManagement.AccountManagement.Employee;
import com.bankManagement.AccountManagement.Login;
import com.bankManagement.Features.ConsoleFeatures;
import org.jetbrains.annotations.Nullable;

/**
 * @Description This is the main class of bank management
 * system. Here are called and used all main methods.
 */

public class BankLauncher extends Menu {
    public BankLauncher() {

    }

    @Override
    protected void showMenuList() {
        System.out.println(
                ConsoleFeatures.PURPLE_BOLD + "WELCOME TO OUR BANK!\n" +
                        ConsoleFeatures.PURPLE +
                        "(1) -> Log in as an employee\n" +
                        "(2) -> Log in as an admin\n" +
                        "(3) -> Log in as an user\n" +
                        "(4) -> Register an account\n" + "(0) -> Exit" +
                        ConsoleFeatures.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                Employee employee = getEmployee();
                if (employee != null) {
                    EmployeePanel employeePanel =
                            new EmployeePanel(employee);
                    employeePanel.run();
                }
                break;
            case 2:
                String login = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Login);
                String password = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Password);
                Login.loginAsAdmin(login, password);
                break;
            case 4:
                RegisterPanel registerPanel = new RegisterPanel();
                registerPanel.run();
                break;
            case 0:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "You have closed the program" +
                        ConsoleFeatures.RESET);
                return false;
            case -1:
                return true;
            default:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "Error [ Wrong input ]" + ConsoleFeatures.RESET);
                System.out.println();
                break;
        }
        return true;
    }

    private static @Nullable Employee getEmployee() {
        String login = ConsoleFeatures.readAccountData(
                ConsoleFeatures.DataReadTypes.Login);
        String password = ConsoleFeatures.readAccountData(
                ConsoleFeatures.DataReadTypes.Password);
        System.out.println("\n");
        return Login.loginAsEmployee(login, password);
    }
}
