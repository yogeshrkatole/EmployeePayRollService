package main.java;

import java.time.LocalDate;

public class EmployeePayRoll {
    private int employeeId;
    private String employeeName;
    private double salary;
    private double deductions;
    private double taxablePay;
    private double incomeTax;
    private double netPay;
    private String phone;
    private String address;
    private String department;
    private LocalDate startDate;
    private String gender;

    public EmployeePayRoll() {}

    public EmployeePayRoll(int employeeId, String employeeName, double salary, double deductions, double taxablePay,
                           double incomeTax, double netPay, String phone, String address, String department, LocalDate startDate,String gender) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.incomeTax = incomeTax;
        this.netPay = netPay;
        this.phone = phone;
        this.address = address;
        this.department = department;
        this.startDate = startDate;
        this.gender = gender;
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

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getTaxablePay() {
        return taxablePay;
    }

    public void setTaxablePay(double taxablePay) {
        this.taxablePay = taxablePay;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Override
    public String toString() {
        return "EmployeePayRoll [employeeId=" + employeeId + ", employeeName=" + employeeName + ", salary=" + salary
                + ", deductions=" + deductions + ", taxablePay=" + taxablePay + ", incomeTax=" + incomeTax
                + ", netPay=" + netPay + ", phone=" + phone + ", address=" + address + ", department=" + department
                + ", startDate=" + startDate + "]";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePayRoll that = (EmployeePayRoll) o;
        return employeeId == that.employeeId &&
                Double.compare(that.salary, salary) == 0 &&
                Double.compare(that.deductions, deductions) == 0 &&
                Double.compare(that.taxablePay, taxablePay) == 0 &&
                Double.compare(that.incomeTax, incomeTax) == 0 &&
                Double.compare(that.netPay, netPay) == 0 &&
                employeeName.equals(that.employeeName) &&
                phone.equals(that.phone) &&
                address.equals(that.address) &&
                department.equals(that.department) &&
                startDate.equals(that.startDate) &&
                gender.equals(that.gender);
    }


}
