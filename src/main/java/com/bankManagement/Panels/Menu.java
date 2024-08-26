package com.bankManagement.Panels;

/**
 * @Description This is an interface which must implement every class
 * that should have a menu. To not define each method bellow many
 * times, I just pushed 'em into one interface and just reuse anytime
 * when i need it
 */

import com.bankManagement.Features.ConsoleFeatures;

public abstract class Menu {
    protected abstract void showMenuList();

    protected abstract boolean executeChoice(int choice);

    private int readChoice() {
        System.out.print("Enter your choice: ");
        return ConsoleFeatures.SCANNER.nextInt();
    }

    protected void run() {
        do {
            showMenuList();
        } while (executeChoice(readChoice()));
    }
}
