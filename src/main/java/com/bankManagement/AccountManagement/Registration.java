package com.bankManagement.AccountManagement;

import com.bankManagement.Database.MySql;
import com.bankManagement.Features.ConsoleFeatures;

import java.sql.*;

public class Registration extends MySql {

    public static void registerAccount(AccountType accountType) {
        int id = getId(accountType);
        try (Connection connection = getConnection()) {
            if (!isValidId(connection, id, accountType)) {
                errorNonValidIdMessage(accountType);
            } else {
                String login = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Login);
                String password = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Password);
                insertIntoAccounts(connection, login, password,
                        id, accountType);
                updateHasAccount(connection, id, accountType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void errorNonValidIdMessage(AccountType accountType) {
        if (accountType == AccountType.employee) {
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ Employee doesn't exists or already " +
                    "has account ]" + ConsoleFeatures.RESET);
        } else {
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ User doesn't exists or already " +
                    "has account ]" + ConsoleFeatures.RESET);
        }
    }

    private static int getId(AccountType accountType) {
        if (accountType == AccountType.employee) {
            System.out.print(
                    ConsoleFeatures.GREEN_BOLD + "Enter employee id: " +
                            ConsoleFeatures.RESET);
        } else {
            System.out.print(
                    ConsoleFeatures.GREEN_BOLD + "Enter user id: " +
                            ConsoleFeatures.RESET);
        }
        return ConsoleFeatures.SCANNER.nextInt();
    }

    private static boolean isValidId(Connection connection, int id,
                                     AccountType accountType)
            throws SQLException {
        String query;
        if (accountType == AccountType.employee) {
            query = "SELECT EXISTS (SELECT 1 FROM " +
                    "employees WHERE id = ? AND has_account = 0)";
        } else {
            query = "SELECT EXISTS (SELECT 1 FROM " +
                    "users WHERE id = ? AND has_account = 0)";
        }
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                return false;
            }
        }
        return true;
    }

    private static void insertIntoAccounts(Connection connection, String login,
                                           String password, int refId,
                                           AccountType accountType)
            throws SQLException {
        String insertQuery;
        if (accountType == AccountType.employee) {
            insertQuery = "INSERT INTO employee_accounts " +
                    "(account_name, account_password, role, " +
                    "employee_id) VALUES" + "(?, ?, 'regular', ?)";
        } else {
            insertQuery = "INSERT INTO user_accounts " +
                    "(username, user_password, user_id) VALUES " +
                    "(?, ?, ?)";
        }
        PreparedStatement statement =
                connection.prepareStatement(insertQuery);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setInt(3, refId);
        statement.executeUpdate();
        statement.close();
    }

    private static void updateHasAccount(Connection connection, int refId,
                                         AccountType accountType)
            throws SQLException {
        String updateQuery;
        if (accountType == AccountType.employee) {
            updateQuery =
                    "UPDATE employees SET has_account = 1 WHERE id = "
                            + refId;
        } else {
            updateQuery =
                    "UPDATE users SET has_account = 1 WHERE id = " + refId;
        }
        connection.prepareStatement(updateQuery).executeUpdate();
    }


}
