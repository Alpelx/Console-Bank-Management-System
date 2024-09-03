package com.bankManagement.Features;

/**
 * @Description This is an abstract class which provides some console
 * features and operations, and also some methods for reading data
 */

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public abstract class ConsoleReading {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static @NotNull String readString(String x) {
        System.out.print(ConsoleTextColors.GREEN_BOLD + x
                + ConsoleTextColors.RESET);
        return SCANNER.next();
    }
}
