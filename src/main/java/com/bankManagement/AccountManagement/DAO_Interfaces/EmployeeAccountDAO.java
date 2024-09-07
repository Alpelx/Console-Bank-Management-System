package com.bankManagement.AccountManagement.DAO_Interfaces;

/**
 * @Description: this is and DAO pattern followed interface, which define
 * operations that must have an employee account.
 */

import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;

public interface EmployeeAccountDAO {
    void updateAccount(EmployeeAccount employeeAccount);

    void addEmployeeAccount(EmployeeAccount employeeAccount);

    void deleteEmployeeAccount(EmployeeAccount employeeAccount);

    EmployeeAccount getEmployeeAccount(int employeeId);

    EmployeeAccount getEmployeeAccount(String login, String password);
}
