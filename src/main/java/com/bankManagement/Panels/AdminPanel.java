package com.bankManagement.Panels;

import com.bankManagement.Features.ConsoleFeatures;

public class AdminPanel extends Menu {
    @Override
    protected void showMenuList() {
        System.out.println(ConsoleFeatures.CYAN_BOLD + "Admin Panel\n" +
                ConsoleFeatures.RESET +
                "[1] -> Display data about users\n" +
                "[2] -> Display data about employees\n" +
                "[3] -> Register an user\n" +
                "[4] -> Hire a new employee\n" +
                "[5] -> Remove an user\n" +
                "[6] -> Dismiss an employee\n" +
                "[7] -> Grant admin to an employee\n" +
                "[8] -> Display an user's transaction history\n" +
                "[9] -> Display whole transaction history" +
                ConsoleFeatures.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "You have closed the admin panel" +
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
