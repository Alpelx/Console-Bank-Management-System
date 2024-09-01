package com.bankManagement.AccountManagement;

/**
 * @Description this is the class that performs the login process of
 * any type of account, regardless of whether it is an employee, admin
 * or a simple user. This class provide login process for each of 'em
 */

import com.bankManagement.Database.MySql;
import com.bankManagement.Exceptions.AccountNotFoundException;
import com.bankManagement.Exceptions.AdminPermissionDeniedException;
import com.bankManagement.Features.ConsoleFeatures;

import java.sql.*;

public abstract class Login extends MySql {
    public static Employee loginAsEmployee(String login, String password) {
        try (Connection connection = getConnection()) {
            if (!isValidAccount(connection, login, password,
                    "employee_accounts")) {
                throw new AccountNotFoundException();
            } else {
                String query = "SELECT e.* FROM employees e, " +
                        "employee_accounts ea WHERE ea.account_name = " +
                        "? and ea.account_password = ? and e.id = " +
                        "ea.employee_id";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    return new Employee(rs.getInt("id"),
                            rs.getString("idnp"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getDate("birthday").toLocalDate(),
                            rs.getString("function_at_work"),
                            rs.getInt("work_experience"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ Wrong login or password ]" +
                    ConsoleFeatures.RESET);
            System.out.println();
        }
        return null;
    }

    public static boolean loginAsAdmin(String login, String password) {
        try (Connection connection = getConnection()) {
            if (!isValidAccount(connection, login, password,
                    "employee_accounts")) {
                throw new AccountNotFoundException();
            } else if (!isAdministrator(connection, login, password)) {
                throw new AdminPermissionDeniedException();
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ Wrong login or password ]" +
                    ConsoleFeatures.RESET);
            System.out.println();
        } catch (AdminPermissionDeniedException e) {
            System.out.println(ConsoleFeatures.RED_BOLD +
                    "Error [ Permission denied ]" +
                    ConsoleFeatures.RESET);
            System.out.println();
        }
        return false;
    }

    private static boolean isValidAccount(Connection connection, String login,
                                          String password, String tableName)
            throws SQLException {
        String query1 = "SELECT EXISTS (SELECT 1 FROM " + tableName +
                " WHERE account_name = ? AND account_password = ?);";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query1);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            return rs.getInt(1) == 1;
        }
        return false;
    }

    private static boolean isAdministrator(Connection connection, String login,
                                              String password) throws SQLException {

        String query = "SELECT role FROM employee_accounts WHERE " +
                "account_name = ? AND account_password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            return rs.getString("role").equals("administrator");
        }
        return false;
    }
}
