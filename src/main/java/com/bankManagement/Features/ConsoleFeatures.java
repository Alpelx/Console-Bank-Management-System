package com.bankManagement.Features;

/**
 * @Description This is an abstract class which provides some console
 * features and operations, and also some methods for reading data
 */

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public abstract class ConsoleFeatures {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String RESET = "\033[0m";
    public static final String CYAN = "\033[0;36m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String PURPLE = "\033[0;35m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String BLUE = "\033[0;34m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String GREEN_BOLD = "\033[1;32m";


    public static @NotNull String readAccountData(DataReadTypes type) {
        if (type == DataReadTypes.Login) {
            System.out.print(
                    ConsoleFeatures.GREEN_BOLD + "Enter your login: " +
                            ConsoleFeatures.RESET);
        } else {
            System.out.print(ConsoleFeatures.GREEN_BOLD +
                    "Enter your password: " + ConsoleFeatures.RESET);
        }
        return SCANNER.next();
    }

    public enum DataReadTypes {
        Login, Password
    }
}
