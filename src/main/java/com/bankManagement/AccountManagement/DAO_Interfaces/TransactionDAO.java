package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.Sources.UserOperationTypes;

import java.util.List;

public interface TransactionDAO {
    Transaction execTransaction(double amount, User user,
                                UserOperationTypes operationType);

    void addTransactionToHistory(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByAccountId(int accountId);
}
