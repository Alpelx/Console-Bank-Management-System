package com.bankManagement.Panels;

/**
 * @Description this is the class what define register panel. Here
 * employees or users are able to register their own account. Only is one
 * account allowed.
 */

import com.bankManagement.AccountManagement.AccountType;
import com.bankManagement.AccountManagement.Registration;
import com.bankManagement.Features.ConsoleFeatures;

public class RegisterPanel extends Menu{
    @Override
    protected void showMenuList() {
        System.out.println(ConsoleFeatures.YELLOW_BOLD +
                "Register menu:\n" + ConsoleFeatures.YELLOW +
                "[1] -> Register as an employee\n" +
                "[2] -> Register as an user\n" +
                "[0] -> Exit" + ConsoleFeatures.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                Registration.registerAccount(AccountType.employee);
                System.out.println("\n\n");
                break;
            case 2:
                Registration.registerAccount(AccountType.user);
                System.out.println("\n\n");
                break;
            case 0:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "You have closed the register panel" +
                        ConsoleFeatures.RESET + "\n\n");
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
