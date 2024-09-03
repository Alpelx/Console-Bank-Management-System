package com.bankManagement.AccountManagement.DAO_Implimentations;

import com.bankManagement.AccountManagement.DAO_Interfaces.UserAccountDAO;
import com.bankManagement.AccountManagement.DAO_Models.UserAccount;
import com.bankManagement.Database.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAccountActions implements UserAccountDAO {
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
    public void updateUserAccount(UserAccount userAccount) {

    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) {

    }
}
