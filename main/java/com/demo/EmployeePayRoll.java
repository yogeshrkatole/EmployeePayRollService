package com.demo;

import java.time.LocalDate;

public class EmployeePayRoll {
    private int employeeId;
    private String employeeName;
    private double salary;
    private LocalDate startDate; 

    
    public EmployeePayRoll() {}


    public EmployeePayRoll(int employeeId, String employeeName, double salary, LocalDate startDate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.startDate = startDate;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

  
    public String toString() {
        return "EmployeePayroll [employeeId=" + employeeId + ", employeeName=" + employeeName + ", salary=" + salary
                + ", startDate=" + startDate + "]";
    }
}

