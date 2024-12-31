
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.EmployeePayRoll;
import main.java.EmployeePayRollException;
import main.java.EmployeePayRollService;
import java.time.LocalDate;

public class AppTest {

	private static Connection connection;
	private EmployeePayRollService payrollService;

	@BeforeClass
	public static void setupDatabaseConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "root";
		connection = DriverManager.getConnection(jdbcURL, username, password);
	}

	@Before
	public void setup() {
		payrollService = new EmployeePayRollService();
	}

	@Test
	public void testGetEmployeeByName() {
		try {
			String employeeName = "Employee 10";
			EmployeePayRoll expectedEmployee = new EmployeePayRoll(10, "Employee 10", 3000000.0, 1400.0, 7100.0,
					1400.0, 5700.0, "5555550010", "Address 10", "Department J", LocalDate.of(2024,01,10),"F");

			EmployeePayRoll actualEmployee = payrollService.getEmployeeByName(connection, employeeName);
			System.out.println(actualEmployee);

			assertNotNull(actualEmployee);
			assertEquals(expectedEmployee, actualEmployee);

		} catch (EmployeePayRollException e) {
			fail("Exception occurred while fetching employee data: " + e.getMessage());
		}
	}

	@AfterClass
	public static void closeDatabaseConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
