package com.bankManagement.AccountManagement;

import com.bankManagement.Database.MySql;
import com.bankManagement.Features.ConsoleFeatures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registering extends MySql {
    public static void registerAsEmployee() {
        System.out.print(
                ConsoleFeatures.GREEN_BOLD + "Enter employee id: " +
                        ConsoleFeatures.RESET);
        int employeeId = ConsoleFeatures.SCANNER.nextInt();
        try (Connection connection = getConnection()) {
            if (!isValidEmployeeID(connection, employeeId)) {
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "Error [ Employee doesn't exists or already " +
                        "has account" + ConsoleFeatures.RESET);
            } else {
                String login = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Login);
                String password = ConsoleFeatures.readAccountData(
                        ConsoleFeatures.DataReadTypes.Password);
                insertIntoEmployeeAccounts(connection, login, password,
                        employeeId);
                updateEmployeeHasAccountValue(connection, employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidEmployeeID(Connection connection, int employeeId)
            throws SQLException {
        String query = "SELECT EXISTS (SELECT 1 FROM " +
                "employees WHERE id = ? AND has_account = 0)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                return false;
            }
        }
        return true;
    }

    private static void insertIntoEmployeeAccounts(Connection connection, String login, String password, int employeeId)
            throws SQLException {
        String insertQuery = "INSERT INTO employee_accounts " +
                "(account_name, account_password, role, " +
                "employee_id) VALUES" + "(?, ?, 'regular', ?)";
        PreparedStatement statement =
                connection.prepareStatement(insertQuery);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setInt(3, employeeId);
        statement.executeUpdate();
        statement.close();
    }

    private static void updateEmployeeHasAccountValue(Connection connection, int employeeId)
            throws SQLException {
        String updateQuery =
                "UPDATE employees SET has_account = 1" + " WHERE id = " +
                        employeeId;
        connection.prepareStatement(updateQuery).executeUpdate();
    }
}
