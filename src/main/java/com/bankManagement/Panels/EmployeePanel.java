package com.bankManagement.Panels;

/**
 * @Description this class define employee panel what appear and is
 * running while employee is logged in. Here are called all methods
 * what perform the employee operations
 */

import com.bankManagement.AccountManagement.Employee;
import com.bankManagement.Features.ConsoleFeatures;

public class EmployeePanel extends Menu {
    private Employee employee;

    public EmployeePanel(Employee employee) {
        this.employee = employee;
    }

    @Override
    protected void showMenuList() {
        System.out.println(
                ConsoleFeatures.CYAN_BOLD + "Employee Panel:\n" +
                        ConsoleFeatures.CYAN +
                        "[1] - Display my data\n" +
                        "[2] - Dismiss from work\n" +
                        "[0] - Close employee panel" +
                        ConsoleFeatures.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(employee);
                break;
            case 0:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "You have closed the program" +
                        ConsoleFeatures.RESET);
                return false;
            default:
                System.out.println(ConsoleFeatures.RED_BOLD +
                        "Error [ Wrong input ]" + ConsoleFeatures.RESET);
                System.out.println();
                break;
        }
        return true;
    }
}
