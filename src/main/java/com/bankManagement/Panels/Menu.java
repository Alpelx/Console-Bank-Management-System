package com.bankManagement.Panels;

/**
 * @Description This is an interface which must implement every class
 * that should have a menu. To not define each method bellow many
 * times, I just pushed 'em into one interface and just reuse anytime
 * when i need it
 */

import com.bankManagement.Features.ConsoleFeatures;

import java.util.InputMismatchException;

public abstract class Menu {
    protected abstract void showMenuList();

    protected abstract boolean executeChoice(int choice);

    private int readChoice() {
        try {
            System.out.print("Enter your choice: ");
            return ConsoleFeatures.SCANNER.nextInt();
        } catch (InputMismatchException e) {
            ConsoleFeatures.SCANNER.next();
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ Please enter a valid number ]" +
                    ConsoleFeatures.RESET);
            System.out.println();
            return -1;
        }


    }

    public void run() {
        do {
            showMenuList();
        } while (executeChoice(readChoice()));
    }
}
