package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;

import java.util.LinkedList;

public interface EmployeeAccountDAO {
    EmployeeAccount getEmployeeAccountByLogin(String login, String password);

    EmployeeAccount getEmployeeAccountByEmployeeId(int employeeId);

    void addEmployeeAccount(EmployeeAccount employeeAccount);

    void updateEmployeeAccount(EmployeeAccount employeeAccount);

    void deleteEmployeeAccount(EmployeeAccount employeeAccount);

    LinkedList<EmployeeAccount> getAllEmployeeAccounts();
}
