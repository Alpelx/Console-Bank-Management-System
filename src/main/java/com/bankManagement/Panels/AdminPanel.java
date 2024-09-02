package com.bankManagement.Panels;

/**
 * @Description this is the class what define admin panel which is opened
 * when an admin is logged in. Here he can do any administrator actions.
 * Each action is defined by own methods with private access modifier for
 * being unable to be called by other classes.
 */

import com.bankManagement.Features.ConsoleTextColors;

public class AdminPanel extends Menu {
    @Override
    protected void showMenuList() {
        System.out.println(ConsoleTextColors.CYAN_BOLD
                + "Admin Panel\n" + ConsoleTextColors.RESET
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

            case 0:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "You have closed the admin panel"
                        + ConsoleTextColors.RESET + "\n\n");
                return false;
            case -1:
                return true;
            default:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "Error [ Wrong input ]"
                        + ConsoleTextColors.RESET);
                System.out.println();
                break;
        }
        return true;
    }

}
