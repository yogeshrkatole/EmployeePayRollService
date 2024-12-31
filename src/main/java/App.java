package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome To Employee Payroll Service");
        

        try {
            Connection connection = getConnection();
            EmployeePayRollService payrollService = new EmployeePayRollService();
            //create table
            payrollService.createEmployeePayrollTable(connection);
            //select employee data
            List<EmployeePayRoll> employeePayrollList = payrollService.getEmployeePayrollData(connection);
            
            System.out.println("---------Employee Payroll Data:--------");
            for (EmployeePayRoll employeePayroll : employeePayrollList) {
                System.out.println(employeePayroll);
            }

            //update employee data
            String employeeName = "Employee 10";
            double updateSalary = 3000000.00;
            int rowsUpdated = payrollService.updateEmployeeSalary(connection, employeeName, updateSalary);
            if (rowsUpdated > 0) {
                System.out.println("Updated salary for " + employeeName + " to " + updateSalary);
            } else {
                System.out.println("No employee found with the name " + employeeName);
            }
            
            //select data by joined date range
            LocalDate startDate = LocalDate.of(2024, 1, 1);
            LocalDate endDate = LocalDate.of(2024, 01, 29);

            List<EmployeePayRoll> employees = payrollService.getEmployeesByDateRange(connection, startDate, endDate);

            System.out.println("------------Employees who joined between " + startDate + " and " + endDate + ":------");
            for (EmployeePayRoll employee : employees) {
                System.out.println(employee);
            }
            
            //print by male and female
            Map<String, Object> analysisResults = payrollService.getGenderBasedSalaryAnalysis(connection);

            analysisResults.forEach((key, value) -> 
                System.out.println(key + ": " + value));

            if (connection != null) {
                connection.close();

                System.out.println("\nconnection closed.");
            }

        } catch (EmployeePayRollException | SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(jdbcURL, username, password);
    }
}
