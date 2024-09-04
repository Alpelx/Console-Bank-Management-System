package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.UserAccount;

public interface UserAccountDAO {
    void addUserAccount(UserAccount userAccount);

    void deleteUserAccount(UserAccount userAccount);

    UserAccount getUserAccount(int user_id);

    UserAccount getUserAccount(String login, String password);
}