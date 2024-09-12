package com.bankManagement.AccountManagement;

import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserActions;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.DAO_Models.UserAccount;

/**
 * @Description: this is the class what provide removing account of an
 * employee or user. The class does not interact directly with mySql,
 * instead it works with DAO models and implementations
 */

public class Removing {
    public static void removeEmployee(Employee employee) {
        EmployeeActions employeeActions = new EmployeeActions();
        EmployeeAccountActions employeeAccountActions
                = new EmployeeAccountActions();
        EmployeeAccount employeeAccount = employeeAccountActions
                .getEmployeeAccount(employee.getId());
        if (employeeAccount != null) {
            employeeAccountActions.deleteEmployeeAccount(employeeAccount);
        }
        employeeActions.deleteEmployee(employee);
    }

    public static void removeUser(User user) {
        UserActions userActions = new UserActions();
        UserAccountActions userAccountActions = new UserAccountActions();
        UserAccount userAccount = userAccountActions
                .getUserAccount(user.getId());
        if (userAccount != null) {
            userAccountActions.deleteUserAccount(userAccount);
        }
        userActions.deleteUser(user);
    }
}
