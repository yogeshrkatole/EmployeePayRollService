package com.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class EmployeePayRollService {
	public List<EmployeePayRoll> getEmployeePayrollData(Connection connection) throws EmployeePayRollException {
        List<EmployeePayRoll> employeePayrollList = new ArrayList<>();
        String query = "SELECT employee_id, employee_name, salary, start_date FROM employee_payroll";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String employeeName = resultSet.getString("employee_name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
          
                EmployeePayRoll employeePayroll = new EmployeePayRoll(employeeId, employeeName, salary, startDate);
                employeePayrollList.add(employeePayroll);
            }

        } catch (SQLException e) {
            throw new EmployeePayRollException("Error while retrieving employee payroll data: " + e.getMessage());
        }

        return employeePayrollList;
    }
}
