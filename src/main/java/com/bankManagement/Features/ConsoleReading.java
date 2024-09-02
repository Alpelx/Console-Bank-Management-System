package com.bankManagement.Features;

/**
 * @Description This is an abstract class which provides some console
 * features and operations, and also some methods for reading data
 */

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public abstract class ConsoleReading {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static @NotNull String readLogin() {
        System.out.print(ConsoleTextColors.GREEN_BOLD
                + "Enter your login: " + ConsoleTextColors.RESET);
        return SCANNER.next();
    }

    public static @NotNull String readPassword() {
        System.out.print(ConsoleTextColors.GREEN_BOLD
                + "Enter your password: " + ConsoleTextColors.RESET);
        return SCANNER.next();
    }
}
