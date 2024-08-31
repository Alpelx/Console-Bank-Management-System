package com.bankManagement.Panels;

import com.bankManagement.AccountManagement.Registering;
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
                Registering.registerAsEmployee();
                break;
            case 0:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "You have closed the register panel" +
                        ConsoleFeatures.RESET);
                break;
            default:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "Error [ Wrong input ]" + ConsoleFeatures.RESET);
                System.out.println();
                return true;
        }
        return false;
    }
}
