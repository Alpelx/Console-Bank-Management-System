package com.bankManagement.Panels;

/**
 * @Description: This is an interface which must implement every class
 * that should have a menu (each panel). It has two abstract classes
 * because their body depends on the specific usage and restrictions
 * of each type of account displayed by each panel respectively.
 * Two methods have described their body here, because it remains the
 * same regardless which panel will use that.
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
