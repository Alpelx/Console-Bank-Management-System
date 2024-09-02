package com.bankManagement.AccountManagement.DAO_Interfaces;

import com.bankManagement.AccountManagement.DAO_Models.Employee;

import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee getEmployeeById(int id);

    Employee getEmployeeByName(String firstname, String lastname);

    List<Employee> getAllEmployees();
}
