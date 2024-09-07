package com.bankManagement.Panels;

/**
 * @Description: this is the class what define register panel. Here
 * employees or users are able to register their own account. Only is one
 * account allowed. Admin account cannot be registered here, because
 * admin privileges must be only granted by another administrators.
 * The class does not interact directly with mySql, instead it works
 * with DAO models and implementations.
 */

import com.bankManagement.Sources.AccountType;
import com.bankManagement.AccountManagement.Registration;
import com.bankManagement.Features.ConsoleTextColors;

public class RegisterPanel extends Menu {
    @Override
    protected void showMenuList() {
        System.out.println(ConsoleTextColors.YELLOW_BOLD
                + "\n\n Register menu:\n" + ConsoleTextColors.YELLOW
                + "[1] -> Register as an employee\n"
                + "[2] -> Register as an user\n"
                + "[0] -> Exit" + ConsoleTextColors.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                Registration.registerAccount(AccountType.employee);
                break;
            case 2:
                Registration.registerAccount(AccountType.user);
                break;
            case 0:
                System.out.println(ConsoleTextColors.RED_BOLD +
                        "You have closed the register panel" +
                        ConsoleTextColors.RESET + "\n\n");
                return false;
            case -1:
                return true;
            default:
                System.out.println(ConsoleTextColors.RED_BOLD +
                        "Error [ Wrong input ]" + ConsoleTextColors.RESET);
                System.out.println();
                break;
        }
        return true;
    }
}
