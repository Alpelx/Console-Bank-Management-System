package com.bankManagement;

import com.bankManagement.AccountManagement.LoggingIn;
import com.bankManagement.Features.ConsoleFeatures;
import com.bankManagement.Panels.Menu;

/**
 * @Description This is the main class of bank management
 * system. Here are called and used all main methods.
 */

public class BankLauncher extends Menu {
    public BankLauncher() {

    }

    @Override
    protected void showMenuList() {
        System.out.println("WELCOME TO OUR BANK!\n" +
                "(1) -> Log in as an employee\n" +
                "(2) -> Log in as an admin\n" + 
                "(3) -> Log in as an user\n" + "(0) -> Exit");
    }

    @Override
    protected boolean executeChoice(int choice) {

        switch (choice) {
            case 1:
                String login = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Login);
                String password = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Password);
                LoggingIn.loginAsEmployee(login, password);
                break;
            case 0:
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
}
