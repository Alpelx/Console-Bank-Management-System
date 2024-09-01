package com.bankManagement.Panels;

/**
 * @Description this is the class what define admin panel which is opened
 * when an admin is logged in. Here he can do any administrator actions.
 * Each action is defined by own methods with private access modifier for
 * being unable to be called by other classes.
 */

import com.bankManagement.AccountManagement.AccountType;
import com.bankManagement.Database.MySql;
import com.bankManagement.Features.ConsoleFeatures;

import java.sql.*;

public class AdminPanel extends Menu {
    @Override
    protected void showMenuList() {
        System.out.println(ConsoleFeatures.CYAN_BOLD + "Admin Panel\n" +
                ConsoleFeatures.RESET +
                "[1] -> Display data about users\n" +
                "[2] -> Display data about employees\n" +
                "[3] -> Register an user\n" +
                "[4] -> Hire a new employee\n" +
                "[5] -> Remove an user\n" +
                "[6] -> Dismiss an employee\n" +
                "[7] -> Grant admin to an employee\n" +
                "[8] -> Revoke admin from an employee\n" +
                "[9] -> Display an user's transaction history\n" +
                "[10] -> Display whole transaction history" +
                ConsoleFeatures.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                displayAll(AccountType.user);
                break;
            case 2:
                displayAll(AccountType.employee);
                break;
            case 3:
                registerUser();
                break;
            case 4:
                hireEmployee();
                break;
            case 5:
                removeUser();
                break;
            case 6:
                dismissEmployee();
                break;
            case 7:
                grantAdmin();
                break;
            case 8:
                revokeAdmin();
                break;
            case 9:
                displayUserTransactionHistory();
                break;
            case 10:
                displayTransactionHistory();
                break;
            case 0:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "You have closed the admin panel" +
                        ConsoleFeatures.RESET + "\n\n");
                return false;
            case -1:
                return true;
            default:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "Error [ Wrong input ]" + ConsoleFeatures.RESET);
                System.out.println();
                break;
        }
        return true;
    }

    private void displayAll(AccountType accountType) {
        try (Connection connection = MySql.getConnection()) {
            String query = accountType == AccountType.user ?
                    "SELECT * FROM users" :
                    "SELECT * FROM employees";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                System.out.println(ConsoleFeatures.BLUE_BOLD +
                        "Information:");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(ConsoleFeatures.BLUE +
                            metaData.getColumnName(i) + " -> " +
                            ConsoleFeatures.RESET + resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUser() {
        System.out.println("register user");
    }

    private void hireEmployee() {
        System.out.println("hire employee");
    }

    private void removeUser() {
        System.out.println("remove user");
    }

    private void dismissEmployee() {
        System.out.println("dismiss employee");
    }

    private void grantAdmin() {
        System.out.println("grant admin");
    }

    private void revokeAdmin() {
        System.out.println("revoke admin");
    }

    private void displayUserTransactionHistory() {
        System.out.println("display user transaction history");
    }

    private void displayTransactionHistory() {
        System.out.println("display transaction history");
    }
}
