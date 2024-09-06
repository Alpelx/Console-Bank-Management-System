package com.bankManagement.AccountManagement.DAO_Implimentations;

/**
 * @Description: this is the class which execute some operations over
 * employee account like getting employee by id or first name, updating
 * account data and others. Here is working with sql queries and executing
 * some
 */

import com.bankManagement.AccountManagement.DAO_Interfaces.EmployeeAccountDAO;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.Database.MySql;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeAccountActions implements EmployeeAccountDAO {
    @Override
    public @Nullable EmployeeAccount getEmployeeAccount(String login,
                                                        String password) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count "
                    + "FROM employee_accounts "
                    + "WHERE account_name = ? "
                    + "AND account_password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (isValidAccount(rs)) {
                return new EmployeeAccount(
                        rs.getInt("id"),
                        rs.getString("account_name"),
                        rs.getString("account_password"),
                        rs.getString("role"),
                        rs.getInt("employee_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @Nullable EmployeeAccount getEmployeeAccount(int employeeId) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count "
                    + "FROM employee_accounts "
                    + "WHERE employee_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (isValidAccount(rs)) {
                return new EmployeeAccount(
                        rs.getInt("id"),
                        rs.getString("account_name"),
                        rs.getString("account_password"),
                        rs.getString("role"),
                        rs.getInt("employee_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidAccount(ResultSet rs) throws SQLException {
        while (rs.next()) {
            if (rs.getInt("Count") == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEmployeeAccount(EmployeeAccount employeeAccount) {
        String query = "INSERT INTO employee_accounts (account_name, "
                + "account_password, role, employee_id) VALUES "
                + "(?, ?, ?, ?)";
        try (Connection connection = MySql.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, employeeAccount.getAccountName());
            stmt.setString(2, employeeAccount.getPassword());
            stmt.setString(3, employeeAccount.getRole());
            stmt.setInt(4, employeeAccount.getEmployeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeAccount(EmployeeAccount employeeAccount) {
        String query = "DELETE FROM employee_accounts WHERE "
                + "employee_id = ?";
        try (Connection connection = MySql.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, employeeAccount.getEmployeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(EmployeeAccount employeeAccount) {
        try (Connection connection = MySql.getConnection()) {
            String query = "UPDATE employee_accounts SET role = ?"
                    + "WHERE employee_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, employeeAccount.getRole());
            stmt.setInt(2, employeeAccount.getEmployeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
