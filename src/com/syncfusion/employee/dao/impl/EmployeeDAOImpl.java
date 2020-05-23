package com.syncfusion.employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.syncfusion.ConnectionManager;
import com.syncfusion.employee.dao.EmployeeDAO;
import com.syncfusion.entity.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	private Connection connection;
	private ConnectionManager connectionManager;
	private String tableName="EMPLOYEE";
	private Boolean isConfirmed;
	private Employee employee;
	private List<Employee> employeeList= new ArrayList<Employee>();
	private char value;
	@Override
	public boolean saveEmployeeDetails(Employee employee) {
		// TODO Auto-generated method stub
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		isConfirmed=employee.getIsConfirmedEmployee();
		if(isConfirmed) {
			value='Y';
		}
		else {
			value='N';
		}
		try {
			PreparedStatement preparedStatement= connection.prepareStatement("Insert into "+tableName+" values(SEQ_EMPLOYEE.nextval,?,?,?,?,?,?)");
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getMobileNumber());
			preparedStatement.setString(3, employee.getRole());
			preparedStatement.setString(4,String.valueOf(value));
			preparedStatement.setInt(5, employee.getMonthlySalary());
			preparedStatement.setDate(6, employee.getDateofJoining());
			if(preparedStatement.execute()) {
				System.out.println(" Row Inserted Successfully...");
				connection.close();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}

	@Override
	public List<Employee> employeeList() {
		// TODO Auto-generated method stub
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sqlQuery="Select * from "+tableName;
			ResultSet result = statement.executeQuery(sqlQuery);
			while(result.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(result.getInt(1));
				employee.setEmployeeName(result.getString(2));
				employee.setMobileNumber(result.getString(3));
				employee.setRole(result.getString(4));
				String value= result.getString(5);
				if(value.equalsIgnoreCase("y")) {
					employee.setIsConfirmedEmployee(true);
				}
				else {
					employee.setIsConfirmedEmployee(false);
				}
				employee.setMonthlySalary(result.getInt(6));
				employee.setDateofJoining(result.getDate(7));
				employeeList.add(employee);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return employeeList;
	}

	@Override
	public Employee getEmployeeDetailsById(String employeeId) {
		// TODO Auto-generated method stub
		
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("Select * from "+tableName+" where employeeId=?");
			preparedStatement.setString(1,employeeId);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {	
					Employee employee = new Employee();
					employee.setEmployeeId(resultset.getInt(1));
					employee.setEmployeeName(resultset.getString(2));
					employee.setMobileNumber(resultset.getString(3));
					employee.setRole(resultset.getString(4));
					String value= resultset.getString(5);
					if(value.equalsIgnoreCase("y")) {
						employee.setIsConfirmedEmployee(true);
					}else {			 
						employee.setIsConfirmedEmployee(false);
					}
					employee.setMonthlySalary(resultset.getInt(6));
					employee.setDateofJoining(resultset.getDate(7));
					System.out.println(employee.getEmployeeName());
					this.employee=employee;			
					connection.close();
				
			}else {
				System.out.println("No matching record found");
				connection.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return employee;
	}

	@Override
	public void updateEmployeeName(Employee employee,String employeeName) {
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		String SQL_QUERY="Update "+tableName+" SET EmployeeName=? where employeeId=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(SQL_QUERY);
			statement.setString(1, employeeName);
			statement.setString(2, employee.getEmployeeId().toString());
			statement.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void updateEmployeeSalary(Employee employee, int employeeSalary) {
		// TODO Auto-generated method stub
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		String SQL_QUERY="Update "+tableName+" SET MonthlySalary=? where employeeId=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(SQL_QUERY);
			statement.setInt(1, employeeSalary);
			statement.setString(2, employee.getEmployeeId().toString());
			statement.executeUpdate();
			employeeList=employeeList();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployeeConfirmation(Employee employee, boolean isConfirmed) {
		// TODO Auto-generated method stub
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		if(isConfirmed) {
			value='Y';
		}
		else {
			value='N';
		}
		String SQL_QUERY="Update "+tableName+" SET isConfirmedEmployee=? where employeeId=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(SQL_QUERY);
			statement.setString(1,String.valueOf(value));
			statement.setString(2, employee.getEmployeeId().toString());
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteEmployeeRecord(String employeeId) {
		// TODO Auto-generated method stub
		connectionManager=ConnectionManager.getInstance();
		connection=connectionManager.getConnectionDetails();
		String SQL_QUERY="Delete from "+tableName+" Where employeeId=?";
		PreparedStatement statement;
		try {
			statement=connection.prepareStatement(SQL_QUERY);
			statement.setString(1, employeeId);
			statement.execute();		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
