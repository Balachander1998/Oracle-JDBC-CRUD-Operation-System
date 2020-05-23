package com.syncfusion;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.syncfusion.employee.dao.EmployeeDAO;
import com.syncfusion.employee.dao.impl.EmployeeDAOImpl;
import com.syncfusion.entity.Employee;

public class EmployeeRunner {
	private Scanner scan;
	private EmployeeDAO employeeDAO;
	public void  addEmployeeDetail() {
		Employee employee = new Employee();
		System.out.println("Enter the employee Name");
		scan= new Scanner(System.in);
		employee.setEmployeeName(scan.nextLine());
		System.out.println("Enter the employee mobile Number...");
		employee.setMobileNumber(scan.nextLine());
		System.out.println("Enter the employee role....");
		employee.setRole(scan.nextLine());
		System.out.println("Is the employee confirmed.?");
		employee.setIsConfirmedEmployee(scan.nextBoolean());
		System.out.println("Enter the salary of employee..");
		employee.setMonthlySalary(scan.nextInt());
		System.out.println("Enter the date of joining of the employee..");
		String dateValue=scan.next();
		employee.setDateofJoining(Date.valueOf(dateValue));
		employeeDAO= new EmployeeDAOImpl();
		employeeDAO.saveEmployeeDetails(employee);
	}
	public void getAllEmployeeDetails() {
		employeeDAO = new EmployeeDAOImpl();
		List<Employee> employeeList= employeeDAO.employeeList();
		for(Employee employee : employeeList) {
			System.out.println(employee.getEmployeeId()+","+employee.getEmployeeName()+","+employee.getMobileNumber()+","+employee.getRole()+","+employee.getIsConfirmedEmployee()+","+employee.getMonthlySalary()+","+employee.getDateofJoining());
		}
	}
	public void updateEmployeeName(String employeeId) {
		employeeDAO= new EmployeeDAOImpl();
		Employee employee=employeeDAO.getEmployeeDetailsById(employeeId);
		System.out.println("Enter the new name for the employee...");
		scan= new Scanner(System.in);
		String employeeName=scan.next();
		employeeDAO.updateEmployeeName(employee,employeeName);
	}
	public void updateEmployeeSalary(String employeeId) {
		employeeDAO = new EmployeeDAOImpl();
		Employee employee = employeeDAO.getEmployeeDetailsById(employeeId);
		System.out.println("Enter the new salary for the employee..");
		scan=new Scanner(System.in);
		int salary= scan.nextInt();
		employeeDAO.updateEmployeeSalary(employee, salary);
	}
	public void updateEmployeeConfirmation(String employeeId) {
		employeeDAO= new EmployeeDAOImpl();
		Employee employee=employeeDAO.getEmployeeDetailsById(employeeId);
		System.out.println("Enter the new confirmation....");
		scan=new Scanner(System.in);
		boolean isConfirmed = scan.nextBoolean();
		employeeDAO.updateEmployeeConfirmation(employee, isConfirmed);
	}
	public void deleteEmployee(String employeeId) {
		employeeDAO=new EmployeeDAOImpl();
		employeeDAO.deleteEmployeeRecord(employeeId);
		System.out.println("Record deleted.....");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Employee Management....");
		EmployeeRunner runner = new EmployeeRunner();
		System.out.println("1. Insert an Employee Record...");
		System.out.println("2. Retrieve all Employees list...");
		System.out.println("3. Update an Employee record...");
		System.out.println("4. Delete an Employee record");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your choice....");
		int choice = scanner.nextInt();
		switch(choice) {
		case 1:
			//Insert the record
			runner.addEmployeeDetail();
			break;
		case 2:
			//Retrieve all Records
			runner.getAllEmployeeDetails();
			break;		
		case 3:
			System.out.println("Enter the field number to update the  record...");
			System.out.println("1.Employee Name");
			System.out.println("2. Monthly Salary");
			System.out.println("3. The employee is confirmed or not..");
			int updateChoice = scanner.nextInt();
			switch(updateChoice) {
			case 1:
			{
				//Update Employee Name...
				System.out.println("Enter the Employee Id....");
				String employeeID=scanner.next();
				runner.updateEmployeeName(employeeID);
				break;
			}
			case 2:
			{
				// Update Monthly Salary
				System.out.println("Enter the Employee Id....");
				String employeeID=scanner.next();
				runner.updateEmployeeSalary(employeeID);
				break;
			}
			case 3:
			{
				//update Whether the employee is confirmed or not
				System.out.println("Enter the Employee Id....");
				String employeeId=scanner.next();
				runner.updateEmployeeConfirmation(employeeId);
			break;
			}
			default:{
				System.out.println("Not found");
			}
			
			}
			break;
		case 4:
		//delete employee Record
		System.out.println("Enter the Employee Id");
		 String employeeId=scanner.next();
		 runner.deleteEmployee(employeeId);
		 break;
		}

	}
}
