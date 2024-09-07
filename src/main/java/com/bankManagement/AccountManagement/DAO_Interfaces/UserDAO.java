package com.bankManagement.AccountManagement.DAO_Interfaces;

/**
 * @Description: this is and DAO pattern followed interface, which define
 * operations that must have a user.
 */

import com.bankManagement.AccountManagement.DAO_Models.User;

import java.util.List;

public interface UserDAO {
    void updateUser(User user);

    void addUser(User user);

    void deleteUser(User user);

    User getUser(String firstName, String lastName);

    User getUser(int id);

    List<User> getAllUsers();
}
