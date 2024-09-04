package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;

import java.util.List;

public interface TransactionDAO {
    Transaction deposit(double amount, User user);

    Transaction withdraw(double amount, int userId);

    Transaction transfer(double amount, int senderId, int receiverId);

    void addTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByAccountId(int accountId);
}
