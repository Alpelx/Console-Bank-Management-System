package com.bankManagement.AccountManagement.DAO_Implimentations;

import com.bankManagement.AccountManagement.DAO_Interfaces.TransactionDAO;
import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.Database.MySql;
import com.bankManagement.Features.ConsoleTextColors;
import com.bankManagement.Sources.UserOperationTypes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionActions implements TransactionDAO {
    @Override
    public Transaction execTransaction(double amount, User user,
                                       UserOperationTypes operationType) {
        try (Connection connection = MySql.getConnection()) {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try {
                UserActions userActions = new UserActions();
                userActions.updateUser(user);
                connection.commit();
                return new Transaction(
                        amount,
                        LocalDateTime.now(),
                        operationType.toString(),
                        user.getId()
                );
            } catch (SQLException e) {
                connection.rollback(savepoint);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addTransactionToHistory(Transaction transaction) {
        try (Connection connection = MySql.getConnection()) {
            String query = "INSERT INTO transactions_history "
                    + "(amount, transaction_date, type, user_id) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, transaction.getAmount());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, transaction.getType());
            stmt.setInt(4, transaction.getUserId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT * FROM transactions_history";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date")
                                .toLocalDateTime(),
                        rs.getString("type"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = MySql.getConnection()) {
            String query = "SELECT * FROM transactions_history "
                    + "WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date")
                                .toLocalDateTime(),
                        rs.getString("type"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
