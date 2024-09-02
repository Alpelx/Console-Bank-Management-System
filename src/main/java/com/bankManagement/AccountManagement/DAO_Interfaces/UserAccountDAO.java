package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.UserAccount;

public interface UserAccountDAO {
    void addUserAccount(UserAccount userAccount);

    void updateUserAccount(UserAccount userAccount);

    void deleteUserAccount(UserAccount userAccount);
}