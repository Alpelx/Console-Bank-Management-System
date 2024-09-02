package com.bankManagement.AccountManagement;

import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeAccountDAOImpl;
import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeDAOImpl;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.Features.ConsoleTextColors;

/**
 * @Description this is the class what provide removing account
 */

public class Removing {
    public static void dismissEmployee(Employee employee) {
        try {
            EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
            EmployeeAccountDAOImpl employeeAccountDAO
                    = new EmployeeAccountDAOImpl();
            EmployeeAccount employeeAccount = employeeAccountDAO
                    .getEmployeeAccountByEmployeeId(employee.getId());
            employeeAccountDAO.deleteEmployeeAccount(employeeAccount);
            employeeDAO.deleteEmployee(employee);
        } catch (NullPointerException e) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ something went wrong ]"
                    + ConsoleTextColors.RESET);
        }
    }
}
