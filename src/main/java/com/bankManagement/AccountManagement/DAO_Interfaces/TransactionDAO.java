package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.Transaction;

import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByAccountId(int accountId);
}
