package com.bankManagement.AccountManagement.DAO_Models;

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
