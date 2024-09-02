package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User getUserByName(String firstName, String lastName);

    List<User> getAllUsers();
}
