package com.bankManagement.AccountManagement.DAO_Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAccount {
    private int id;
    private String accountName;
    private String password;
    private String role;
    private int employeeId;

    public EmployeeAccount(int id, String accountName, String password,
                           String role, int employeeId) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
        this.role = role;
        this.employeeId = employeeId;
    }

    public EmployeeAccount(String accountName, String password,
                           String role, int employeeId) {
        this.accountName = accountName;
        this.password = password;
        this.role = role;
        this.employeeId = employeeId;
    }
}
