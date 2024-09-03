package com.bankManagement.AccountManagement.DAO_Implimentations;

import com.bankManagement.AccountManagement.DAO_Interfaces.UserDAO;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.Database.MySql;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserActions implements UserDAO {
    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = MySql.getConnection()) {
            String query = "UPDATE users SET balance = ?, "
                    + "has_account = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, user.getBalance());
            stmt.setBoolean(2, true);
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public @Nullable User getUser(String firstName, String lastName) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count FROM users "
                    + "WHERE firstname = ? AND lastname = ? "
                    + "GROUP BY users.id, users.idnp, users.firstname, "
                    + "users.lastname, users.birthday, users.balance, "
                    + "users.has_account ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            ResultSet rs = stmt.executeQuery();
            if (isValidUser(rs)) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("idnp"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getDouble("balance"),
                        rs.getBoolean("has_account")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(int id) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count FROM users "
                    + "WHERE id = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (isValidUser(rs)) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("idnp"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getDouble("balance"),
                        rs.getBoolean("has_account")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isValidUser(ResultSet rs) throws SQLException {
        while (rs.next()) {
            if (rs.getInt("Count") == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(getUser(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
