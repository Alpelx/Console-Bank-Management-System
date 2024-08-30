package com.bankManagement.AccountManagement;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.bankManagement.Database.MySql;
import com.bankManagement.Exceptions.AccountNotFoundException;
import com.bankManagement.Features.ConsoleFeatures;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class LoggingIn extends MySql {
    public static @NotNull void loginAsEmployee(String login, String password) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            if (!isValidAccount(statement, login, password)) {
                throw new AccountNotFoundException();
            } else {
                System.out.println("Welcome " + login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ Wrong login or password ]" +
                    ConsoleFeatures.RESET);
            System.out.println();
        }
    }

    private static boolean isValidAccount(Statement statement, String login, String password) {
        String query1 =
                "SELECT EXISTS (SELECT 1 FROM employee_accounts " +
                        "WHERE account_name = '" + login + "');";
        String query2 =
                "SELECT account_password FROM employee_accounts " +
                        "WHERE account_name = '" + login + "';";
        try {
            return isValidLogin(statement, query1) &&
                    isValidPassword(statement, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isValidPassword(Statement statement, String password, String query2)
            throws SQLException {
        ResultSet resultSet;
        resultSet = statement.executeQuery(query2);
        String hashedPassword = "";
        while (resultSet.next()) {
            hashedPassword = resultSet.getString(1);
        }
        return BCrypt.verifyer()
                .verify(password.toCharArray(), hashedPassword).verified;
    }

    private static boolean isValidLogin(Statement statement, String query1)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(query1);
        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                return true;
            }
        }
        return false;
    }
}
