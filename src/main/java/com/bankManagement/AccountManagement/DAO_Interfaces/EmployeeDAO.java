package com.bankManagement.AccountManagement.DAO_Interfaces;

/**
 * @Description: this is and DAO pattern followed interface, which define
 * operations that must have an employee.
 */

import com.bankManagement.AccountManagement.DAO_Models.Employee;

import java.util.List;

public interface EmployeeDAO {
    void updateEmployee(Employee employee);

    void addEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee getEmployeeById(int id);

    Employee getEmployee(String firstname, String lastname);

    List<Employee> getAllEmployees();
}
