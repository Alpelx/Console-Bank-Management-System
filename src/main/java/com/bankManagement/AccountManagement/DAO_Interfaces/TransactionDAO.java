package com.bankManagement.AccountManagement.DAO_Interfaces;

/**
 * @Description: this is and DAO pattern following interface, which define
 * operations that must execute a transactions.
 */

import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.Sources.TransactionTypes;

import java.util.List;

public interface TransactionDAO {
    Transaction execTransaction(double amount, User user,
                                TransactionTypes operationType);

    void addTransactionToHistory(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByAccountId(int accountId);
}
