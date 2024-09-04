package com.bankManagement.Panels;

import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.Features.ConsoleTextColors;

public class UserPanel extends Menu {
    private User user;

    public UserPanel(User user) {
        this.user = user;
    }

    @Override
    protected void showMenuList() {
        System.out.println(ConsoleTextColors.CYAN_BOLD
                + "\n\nUser Panel\n" + ConsoleTextColors.RESET
                + "[1] -> Display my data\n"
                + "[2] -> Display my balance\n"
                + "[3] -> Make a deposit\n"
                + "[4] -> Make a withdrawal\n"
                + "[5] -> Make a transfer\n"
                + "[6] -> Remove my account"
                + ConsoleTextColors.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(user);
                break;
            case 2:
                System.out.println(ConsoleTextColors.BLUE_BOLD
                        +"My balance: " + ConsoleTextColors.BLUE
                        + user.getBalance() + " MDL");
                break;
            case 0:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "You have closed the admin panel"
                        + ConsoleTextColors.RESET + "\n\n");
                return false;
            case -1:
                break;
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
