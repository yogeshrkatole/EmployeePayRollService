package main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayRollService {

    public List<EmployeePayRoll> getEmployeePayrollData(Connection connection) throws EmployeePayRollException {
        List<EmployeePayRoll> employeePayrollList = new ArrayList<>();
        String query = "SELECT * FROM employee_payroll";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String employeeName = resultSet.getString("employee_name");
                double salary = resultSet.getDouble("salary");
                double deductions = resultSet.getDouble("deductions");
                double taxablePay = resultSet.getDouble("taxable_pay");
                double incomeTax = resultSet.getDouble("income_tax");
                double netPay = resultSet.getDouble("net_pay");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String department = resultSet.getString("department_name");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();

                EmployeePayRoll employeePayroll = new EmployeePayRoll(employeeId, employeeName, salary, deductions,
                        taxablePay, incomeTax, netPay, phone, address, department, startDate);
                employeePayrollList.add(employeePayroll);
            }

        } catch (SQLException e) {
            throw new EmployeePayRollException("Error while retrieving employee payroll data: " + e.getMessage());
        }

        return employeePayrollList;
    }

    public int updateEmployeeSalary(Connection connection, String employeeName, double updateSalary) throws EmployeePayRollException {
        String updateQuery = "UPDATE employee_payroll SET salary = ? WHERE employee_name = ?";
        int rowsUpdated = 0;

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setDouble(1, updateSalary);
            statement.setString(2, employeeName);  
            rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new EmployeePayRollException("No employee found with the name: " + employeeName);
            }
            
            return rowsUpdated;
        } catch (SQLException e) {
            throw new EmployeePayRollException("Error while updating salary: " + e.getMessage());
        }
    }


    public EmployeePayRoll getEmployeeByName(Connection connection, String employeeName) throws EmployeePayRollException {
        String query = "SELECT * FROM employee_payroll WHERE employee_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                double salary = resultSet.getDouble("salary");
                double deductions = resultSet.getDouble("deductions");
                double taxablePay = resultSet.getDouble("taxable_pay");
                double incomeTax = resultSet.getDouble("income_tax");
                double netPay = resultSet.getDouble("net_pay");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String department = resultSet.getString("department_name");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();

                return new EmployeePayRoll(employeeId, employeeName, salary, deductions, taxablePay, incomeTax, netPay,
                        phone, address, department, startDate);
            } else {
                throw new EmployeePayRollException("No employee found with the name: " + employeeName);
            }

        } catch (SQLException e) {
            throw new EmployeePayRollException("Error while retrieving employee data: " + e.getMessage());
        }
    }
    
    public List<EmployeePayRoll> getEmployeesByDateRange(Connection connection, LocalDate startDate, LocalDate endDate) throws EmployeePayRollException {
        List<EmployeePayRoll> employeePayrollList = new ArrayList<>();
        String query = "SELECT * FROM employee_payroll WHERE start_date BETWEEN ? AND ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(startDate));
            statement.setDate(2, java.sql.Date.valueOf(endDate));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String employeeName = resultSet.getString("employee_name");
                double salary = resultSet.getDouble("salary");
                double deductions = resultSet.getDouble("deductions");
                double taxablePay = resultSet.getDouble("taxable_pay");
                double incomeTax = resultSet.getDouble("income_tax");
                double netPay = resultSet.getDouble("net_pay");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String department = resultSet.getString("department_name");
                LocalDate start = resultSet.getDate("start_date").toLocalDate();

                EmployeePayRoll employee = new EmployeePayRoll(employeeId, employeeName, salary, deductions, taxablePay, incomeTax, netPay, phone, address, department, start);
                employeePayrollList.add(employee);
            }

        } catch (SQLException e) {
            throw new EmployeePayRollException("Error while retrieving employees by date range: " + e.getMessage());
        }
        return employeePayrollList;
    }

}
