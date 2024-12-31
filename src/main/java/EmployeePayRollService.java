package main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePayRollService {

	public void createEmployeePayrollTable(Connection connection) throws EmployeePayRollException {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS employee_payroll (" + "employee_id INT PRIMARY KEY, "
				+ "employee_name VARCHAR(255) NOT NULL, " + "salary DOUBLE NOT NULL, " + "deductions DOUBLE NOT NULL, "
				+ "taxable_pay DOUBLE NOT NULL, " + "income_tax DOUBLE NOT NULL, " + "net_pay DOUBLE NOT NULL, "
				+ "phone VARCHAR(15), " + "address VARCHAR(255), " + "department_name VARCHAR(100), "
				+ "start_date DATE NOT NULL, " + "gender CHAR(1) CHECK (gender IN ('F', 'M'))" + ")";

		try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
			statement.executeUpdate();
			System.out.println("Employee payroll table created successfully.");
		} catch (SQLException e) {
			throw new EmployeePayRollException("Error while creating employee payroll table: " + e.getMessage());
		}
	}

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
				String gender = resultSet.getString("gender");

				EmployeePayRoll employeePayroll = new EmployeePayRoll(employeeId, employeeName, salary, deductions,
						taxablePay, incomeTax, netPay, phone, address, department, startDate,gender);
				employeePayrollList.add(employeePayroll);
			}

		} catch (SQLException e) {
			throw new EmployeePayRollException("Error while retrieving employee payroll data: " + e.getMessage());
		}

		return employeePayrollList;
	}

	public int updateEmployeeSalary(Connection connection, String employeeName, double updateSalary)
			throws EmployeePayRollException {
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

	public EmployeePayRoll getEmployeeByName(Connection connection, String employeeName)
			throws EmployeePayRollException {
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
				String gender = resultSet.getString("gender");

				return new EmployeePayRoll(employeeId, employeeName, salary, deductions, taxablePay, incomeTax, netPay,
						phone, address, department, startDate,gender);
			} else {
				throw new EmployeePayRollException("No employee found with the name: " + employeeName);
			}

		} catch (SQLException e) {
			throw new EmployeePayRollException("Error while retrieving employee data: " + e.getMessage());
		}
	}

	public List<EmployeePayRoll> getEmployeesByDateRange(Connection connection, LocalDate startDate, LocalDate endDate)
			throws EmployeePayRollException {
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
				String gender = resultSet.getString("gender");

				EmployeePayRoll employee = new EmployeePayRoll(employeeId, employeeName, salary, deductions, taxablePay,
						incomeTax, netPay, phone, address, department, start,gender);
				employeePayrollList.add(employee);
			}

		} catch (SQLException e) {
			throw new EmployeePayRollException("Error while retrieving employees by date range: " + e.getMessage());
		}
		return employeePayrollList;
	}

	
	public Map<String, Object> getGenderBasedSalaryAnalysis(Connection connection) throws EmployeePayRollException {
        Map<String, Object> analysisResults = new HashMap<>();
        String query = "SELECT gender, SUM(salary) AS total_salary, AVG(salary) AS average_salary, " +
                       "MIN(salary) AS minimum_salary, MAX(salary) AS maximum_salary, COUNT(*) AS employee_count " +
                       "FROM employee_payroll GROUP BY gender";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double totalSalary = resultSet.getDouble("total_salary");
                double averageSalary = resultSet.getDouble("average_salary");
                double minSalary = resultSet.getDouble("minimum_salary");
                double maxSalary = resultSet.getDouble("maximum_salary");
                int employeeCount = resultSet.getInt("employee_count");

                analysisResults.put(gender + "_total_salary", totalSalary);
                analysisResults.put(gender + "_average_salary", averageSalary);
                analysisResults.put(gender + "_min_salary", minSalary);
                analysisResults.put(gender + "_max_salary", maxSalary);
                analysisResults.put(gender + "_employee_count", employeeCount);
            }
        } catch (SQLException e) {
            throw new EmployeePayRollException("Error while retrieving gender-based salary analysis: " + e.getMessage());
        }

        return analysisResults;
    }
}
