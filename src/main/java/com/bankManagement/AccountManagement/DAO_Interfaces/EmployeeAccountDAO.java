package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;

public interface EmployeeAccountDAO {
    EmployeeAccount getEmployeeAccount(String login, String password);

    EmployeeAccount getEmployeeAccount(int employeeId);

    void addEmployeeAccount(EmployeeAccount employeeAccount);

    void deleteEmployeeAccount(EmployeeAccount employeeAccount);

    void updateAccount(EmployeeAccount employeeAccount);
}
