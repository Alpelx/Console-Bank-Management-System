package com.bankManagement.AccountManagement.DAO_Implimentations;

import com.bankManagement.AccountManagement.DAO_Interfaces.EmployeeDAO;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.Database.MySql;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeActions implements EmployeeDAO {
    @Override
    public void addEmployee(Employee employee) {
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
            stmt.setBoolean(3, employee.isHas_account());
            stmt.setInt(4, employee.getId());
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
    public @Nullable Employee getEmployee(String firstname,
                                          String lastname) {
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

    private boolean isValidEmployee(ResultSet rs)
            throws SQLException {
        while (rs.next()) {
            if (rs.getInt("Count") == 1) {
                return true;
            }
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
        } catch (SQLException e){
            e.printStackTrace();
        }
        return employees;
    }
}
