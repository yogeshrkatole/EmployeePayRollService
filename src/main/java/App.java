package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome To Employee Payroll Service");
        

        try {
            Connection connection = getConnection();
            EmployeePayRollService payrollService = new EmployeePayRollService();
            List<EmployeePayRoll> employeePayrollList = payrollService.getEmployeePayrollData(connection);
            
            System.out.println("Employee Payroll Data:");
            for (EmployeePayRoll employeePayroll : employeePayrollList) {
                System.out.println(employeePayroll);
            }

            String employeeName = "Employee 10";
            double updateSalary = 3000000.00;
            int rowsUpdated = payrollService.updateEmployeeSalary(connection, employeeName, updateSalary);
            if (rowsUpdated > 0) {
                System.out.println("Updated salary for " + employeeName + " to " + updateSalary);
            } else {
                System.out.println("No employee found with the name " + employeeName);
            }
            

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
