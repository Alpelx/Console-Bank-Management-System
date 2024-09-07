package com.bankManagement.AccountManagement.DAO_Implimentations;

/**
 * @Description: this is the class that describe the functionality above
 * an employee. Here are functionalities like adding, updating, removing
 * and getting an employee by some specific parameters. The class interacts
 * directly with sql, and further methods will be called for interacting
 * with database through the non sql way.
 */

import com.bankManagement.AccountManagement.DAO_Interfaces.EmployeeDAO;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.Database.MySql;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeActions implements EmployeeDAO {
    public EmployeeActions() {
    }

    @Override
    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET function_at_work = ?, "
                + "work_experience = ?, has_account = ? WHERE "
                + "id = ?";
        try (Connection connection = MySql.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, employee.getFunctionAtWork());
            stmt.setInt(2, employee.getWorkExperience());
            stmt.setBoolean(3, employee.isAccount());
            stmt.setInt(4, employee.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        try (Connection connection = MySql.getConnection()) {
            String query = "INSERT INTO employees (idnp, firstname, "
                    + "lastname, birthday, function_at_work, "
                    + "work_experience, has_account) VALUES (?, ?, ?, ?, ?, ?,"
                    + "DEFAULT)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, employee.getIdnp());
            stmt.setString(2, employee.getFirstname());
            stmt.setString(3, employee.getLastname());
            stmt.setDate(4, Date.valueOf(employee.getDateOfBirth()));
            stmt.setString(5, employee.getFunctionAtWork());
            stmt.setInt(6, employee.getWorkExperience());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = MySql.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, employee.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @Nullable Employee getEmployeeById(int id) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count FROM employees "
                    + "WHERE id = " + id;
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (isValidEmployee(rs)) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("idnp"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("function_at_work"),
                        rs.getInt("work_experience"),
                        rs.getBoolean("has_account")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @Nullable Employee getEmployee(String firstname, String lastname) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count FROM employees "
                    + "WHERE firstname = ? AND lastname = ?"
                    + "GROUP BY employees.id, employees.idnp, "
                    + "employees.firstname, employees.lastname, "
                    + "employees.birthday, employees.function_at_work, "
                    + "employees.work_experience, employees.has_account";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            ResultSet rs = stmt.executeQuery();
            if (isValidEmployee(rs)) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("idnp"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("function_at_work"),
                        rs.getInt("work_experience"),
                        rs.getBoolean("has_account")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidEmployee(ResultSet rs) throws SQLException {
        while (rs.next()) {
            return rs.getInt("Count") == 1;
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT * FROM employees";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(getEmployeeById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
