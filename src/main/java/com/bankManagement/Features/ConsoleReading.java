package com.bankManagement.Features;

/**
 * @Description This is an abstract class which provides some console
 * features and operations, and also some methods for reading data
 */

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class ConsoleReading {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static @NotNull String readString(String x) {
        System.out.print(ConsoleTextColors.GREEN_BOLD + x
                + ConsoleTextColors.RESET);
        return SCANNER.next();
    }

    public static int readInt(String x) {
        System.out.print(ConsoleTextColors.GREEN_BOLD + x
                + ConsoleTextColors.RESET);
        return SCANNER.nextInt();
    }

    public static @NotNull LocalDate readLocalDate() {
        LocalDate dateOfBirth;
        while (true) {
            try {
                String dateOfBirthStr =
                        readString("Enter date of birth (YYYY-MM-DD): ");
                dateOfBirth = LocalDate.parse(dateOfBirthStr);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "Invalid format or date"
                        + ConsoleTextColors.RESET);
            }
        }
        return dateOfBirth;
    }
}
