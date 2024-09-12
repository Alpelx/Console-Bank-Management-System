package com.bankManagement.Panels;

/**
 * @Description: this is the class what define employee panel which is
 * opened when an employee is logged in. Here are described employee
 * actions that he can do. The class does not interact directly with
 * mySql, instead it works with DAO models and implementations
 */

import com.bankManagement.AccountManagement.DAO_Models.Employee;
import com.bankManagement.AccountManagement.Removing;
import com.bankManagement.Features.Colorable;

public class EmployeePanel extends Menu {
    private final Employee employee;

    public EmployeePanel(Employee employee) {
        this.employee = employee;
    }

    @Override
    protected void showMenuList() {
        System.out.println(Colorable.CYAN_BOLD
                + "\n\nEmployee Panel:\n" + Colorable.CYAN
                + "[1] -> Display my data\n"
                + "[2] -> Dismiss from work\n"
                + "[0] -> Close employee panel"
                + Colorable.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(employee);
                break;
            case 2:
                Removing.removeEmployee(employee);
                System.out.println(Colorable.GREEN_BOLD
                        + "Employee removed successfully"
                        + Colorable.RESET);
                return false;
            case 0:
                System.out.println(Colorable.RED_BOLD
                        + "You have closed the employee panel"
                        + Colorable.RESET + "\n\n");
                return false;
            case -1:
                break;
            default:
                System.out.println(Colorable.RED_BOLD
                        + "Error [ Wrong input ]"
                        + Colorable.RESET + "\n");
                break;
        }
        return true;
    }
}
