package com.bankManagement.Features;

/**
 * This is an abstract class which provides some console features and
 * operations, and also some methods for reading data
 */

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public abstract class ConsoleFeatures {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static @NotNull String readAccountData(DataReadTypes type) {
        System.out.print(
                type == DataReadTypes.Login ? "Enter your login: " :
                        "Enter your password: ");
        return SCANNER.next();
    }

    public static @NotNull String hashPassword() {
        return BCrypt.withDefaults().hashToString(12,
                readAccountData(DataReadTypes.Password).toCharArray());
    }

    public enum DataReadTypes {
        Login, Password
    }
}
