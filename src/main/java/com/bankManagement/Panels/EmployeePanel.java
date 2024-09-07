package com.bankManagement.Panels;

/**
 * @Description: this is the class what define employee panel which is
 * opened when an employee is logged in. Here are described employee
 * actions that he can do. The class does not interact directly with
 * mySql, instead it works with DAO models and implementations
 */

import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.Removing;
import com.bankManagement.Features.ConsoleTextColors;

public class EmployeePanel extends Menu {
    private final Employee employee;

    public EmployeePanel(Employee employee) {
        this.employee = employee;
    }

    @Override
    protected void showMenuList() {
        System.out.println(ConsoleTextColors.CYAN_BOLD
                + "\n\nEmployee Panel:\n" + ConsoleTextColors.CYAN
                + "[1] -> Display my data\n"
                + "[2] -> Dismiss from work\n"
                + "[0] -> Close employee panel"
                + ConsoleTextColors.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(employee);
                break;
            case 2:
                Removing.removeEmployee(employee);
                return false;
            case 0:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "You have closed the employee panel"
                        + ConsoleTextColors.RESET + "\n\n");
                return false;
            case -1:
                break;
            default:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "Error [ Wrong input ]"
                        + ConsoleTextColors.RESET + "\n");
                break;
        }
        return true;
    }
}
