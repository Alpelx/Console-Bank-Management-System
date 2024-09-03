package com.bankManagement.Panels;

/**
 * @Description this class define employee panel what appear and is
 * running while employee is logged in. Here are called all methods
 * what perform the employee operations
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
                return true;
            default:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "Error [ Wrong input ]"
                        + ConsoleTextColors.RESET);
                System.out.println();
                break;
        }
        return true;
    }
}
