package com.bankManagement.Panels;

/**
 * @Description This is an interface which must implement every class
 * that should have a menu. To not define each method bellow many
 * times, I just pushed 'em into one interface and just reuse anytime
 * when i need it
 */

import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.ConsoleTextColors;

import java.util.InputMismatchException;

public abstract class Menu {
    protected abstract void showMenuList();

    protected abstract boolean executeChoice(int choice);

    private int readChoice() {
        try {
            System.out.print(ConsoleTextColors.GREEN_BOLD
                    + "Enter your choice: " + ConsoleTextColors.RESET);
            return ConsoleReading.SCANNER.nextInt();
        } catch (InputMismatchException e) {
            ConsoleReading.SCANNER.next();
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ Please enter a valid number ]"
                    + ConsoleTextColors.RESET);
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
