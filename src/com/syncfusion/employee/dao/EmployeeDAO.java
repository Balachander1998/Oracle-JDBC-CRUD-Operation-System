package com.syncfusion.employee.dao;

import java.util.List;

import com.syncfusion.entity.Employee;

public interface EmployeeDAO {
	
	public boolean saveEmployeeDetails(Employee employee);
	public List<Employee> employeeList();
	public Employee getEmployeeDetailsById(String employeeId);
	public void updateEmployeeName(Employee employee,String employeeName);
	public void updateEmployeeSalary(Employee employee, int employeeSalary);
	public void updateEmployeeConfirmation(Employee employee, boolean isConfirmed);
	public void deleteEmployeeRecord(String employeeId);
	
}
