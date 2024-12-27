package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App 
{
	
    public static void main( String[] args )
    {
        System.out.println( "Welcome To Employee PayRoll Service" );
        EmployeePayRollService payrollService = new EmployeePayRollService();

        try {
            Connection connection = getConnection();

            List<EmployeePayRoll> employeePayrollList = payrollService.getEmployeePayrollData(connection);

            for (EmployeePayRoll employeePayroll : employeePayrollList) {
                System.out.println(employeePayroll);
            }

            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
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
