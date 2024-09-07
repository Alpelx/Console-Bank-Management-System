package com.bankManagement.AccountManagement.DAO_Models;

/**
 * @Description: This is the class that define a user account to make
 * easier managing his data and operations through the java program.
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccount {
    private int id;
    private String username;
    private String password;
    private int userId;

    public UserAccount(int id, String username, String password,
                       int userId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public UserAccount(String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }
}
