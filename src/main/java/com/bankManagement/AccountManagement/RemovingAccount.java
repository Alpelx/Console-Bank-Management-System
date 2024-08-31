package com.bankManagement.AccountManagement;

import com.bankManagement.Database.MySql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemovingAccount extends MySql {
    public static void dismissEmployee(int employeeId) {
        try (Connection connection = getConnection()) {
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
