package com.bankManagement.AccountManagement;

/**
 * @Description this is the class what provide removing account
 */

import com.bankManagement.Database.MySql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemovingAccount {
    public static void dismissEmployee(int employeeId) {
        try (Connection connection = MySql.getConnection()) {
            String removeAccountQuery =
                    "DELETE FROM employee_accounts WHERE " +
                            "employee_id = " + employeeId;
            String removeEmployee =
                    "DELETE FROM employees WHERE id = " + employeeId;
            Statement statement = connection.createStatement();
            statement.executeUpdate(removeAccountQuery);
            statement.executeUpdate(removeEmployee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
