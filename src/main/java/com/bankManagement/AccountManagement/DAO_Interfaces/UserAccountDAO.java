package com.bankManagement.AccountManagement.DAO_Interfaces;

/**
 * @Description: this is and DAO pattern followed interface, which define
 * operations that must have a user account.
 */

import com.bankManagement.AccountManagement.DAO_Models.UserAccount;

public interface UserAccountDAO {
    void addUserAccount(UserAccount userAccount);

    void deleteUserAccount(UserAccount userAccount);

    UserAccount getUserAccount(int user_id);

    UserAccount getUserAccount(String login, String password);
}