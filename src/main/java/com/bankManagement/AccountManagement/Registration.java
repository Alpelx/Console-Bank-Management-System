package com.bankManagement.AccountManagement;

import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeAccountDAOImpl;
import com.bankManagement.AccountManagement.DAO_Implimentations.EmployeeDAOImpl;
import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.DAO_Models.EmployeeAccount;
import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.ConsoleTextColors;

/**
 * @Description this is the class where is performed account registration
 * for an employee or user. There are one main public methods what is
 * called to register and some private methods to increase readability
 * and make code cleaner
 */

public abstract class Registration {
    public static void registerEmployee() {
        System.out.print(ConsoleTextColors.GREEN_BOLD
                + "Enter employee firstname: " + ConsoleTextColors.RESET);
        String firstname = ConsoleReading.SCANNER.next();
        System.out.print(ConsoleTextColors.GREEN_BOLD
                + "Enter employee lastname: " + ConsoleTextColors.RESET);
        String lastname = ConsoleReading.SCANNER.next();
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        Employee employee = employeeDAO.getEmployeeByName(firstname,
                lastname);
        if (employee == null || employee.isHas_account()) {
            employeeErrorMessage();
        } else {
            registerEmployeeData(employee, employeeDAO);
        }
    }

    private static void employeeErrorMessage() {
        System.out.println(ConsoleTextColors.RED_BOLD
                + "Error [ Employee doesn't exist or already "
                + "has account ]" + ConsoleTextColors.RESET);
    }

    private static void registerEmployeeData(Employee employee,
                                             EmployeeDAOImpl employeeDAO) {
        String login = ConsoleReading.readLogin();
        String password = ConsoleReading.readPassword();
        EmployeeAccount employeeAccount = new EmployeeAccount(
                login, password, "regular", employee.getId()
        );
        EmployeeAccountDAOImpl employeeAccountDAO =
                new EmployeeAccountDAOImpl();
        employeeAccountDAO.addEmployeeAccount(employeeAccount);
        employee.setHas_account(true);
        employeeDAO.updateEmployee(employee);
    }
}
