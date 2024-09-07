package com.bankManagement.AccountManagement.DAO_Implimentations;

/**
 * @Description: this is the class which execute some operations over
 * user account like getting user by id or first name, updating
 * account data and others. The class interact directly with mySql, and
 * further the methods will be called for interaction with database through
 * the non sql way.
 */

import com.bankManagement.AccountManagement.DAO_Interfaces.UserAccountDAO;
import com.bankManagement.AccountManagement.DAO_Models.UserAccount;
import com.bankManagement.Database.MySql;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountActions implements UserAccountDAO {
    public UserAccountActions() {
    }

    @Override
    public void addUserAccount(UserAccount userAccount) {
        try (Connection connection = MySql.getConnection()) {
            String query = "INSERT INTO user_accounts (username, "
                    + "user_password, user_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userAccount.getUsername());
            stmt.setString(2, userAccount.getPassword());
            stmt.setInt(3, userAccount.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) {
        String query = "DELETE FROM user_accounts WHERE "
                + "user_id = ?";
        try (Connection connection = MySql.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userAccount.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @Nullable UserAccount getUserAccount(int user_id) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count FROM user_accounts "
                    + "WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            if (isValidAccount(rs)) {
                return new UserAccount(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("user_password"),
                        rs.getInt("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @Nullable UserAccount getUserAccount(String login, String password) {
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT *, COUNT(*) AS Count "
                    + "FROM user_accounts "
                    + "WHERE username = ? "
                    + "AND user_password = ? "
                    + "GROUP BY user_accounts.id, user_accounts.username, "
                    + "user_accounts.user_password, user_accounts.user_id ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (isValidAccount(rs)) {
                return new UserAccount(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("user_password"),
                        rs.getInt("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidAccount(ResultSet rs) throws SQLException {
        while (rs.next()) {
            return rs.getInt("Count") == 1;
        }
        return false;
    }
}
