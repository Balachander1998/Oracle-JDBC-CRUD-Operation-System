package com.syncfusion.entity;

import java.sql.Date;

public class Employee {
	private Integer employeeId;
	private String employeeName;
	private String mobileNumber;
	private String role;
	private Boolean isConfirmedEmployee;
	private Integer monthlySalary;
	private Date dateofJoining;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsConfirmedEmployee() {
		return isConfirmedEmployee;
	}

	public void setIsConfirmedEmployee(Boolean isConfirmedEmployee) {
		this.isConfirmedEmployee = isConfirmedEmployee;
	}

	public Integer getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Integer monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Date getDateofJoining() {
		return dateofJoining;
	}

	public void setDateofJoining(Date dateofJoining) {
		this.dateofJoining = dateofJoining;
	}

}
