package lab1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Q1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 1. Establish Connection
		// 1.1 Create Data Source and set properties
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://localhost:3306/salespersonDB");
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		try {
			// 1.2 Create connection object
			Connection conn = mysqlDS.getConnection();

			// 2. Create a Statement
			// 2.1 The Statement object is used for executing a static SQL
			// statement and returning the results it produces
			Statement myStmt = conn.createStatement();
			String query = "SELECT * FROM salesperson_table";

			// 2.2 The PreparedStatement object is used for executing SQL
			// statements whose values may vary and returning the results
			String prepQuery = "SELECT * FROM salesperson_table WHERE commission<=?";
			PreparedStatement myPrepStmt = conn.prepareStatement(prepQuery);
			// Get user input for prepQuery
			System.out.println("Enter max commission you wish to display: ");
			double maxComm = scanner.nextDouble();
			// Set the value for the PreparedStatement
			myPrepStmt.setDouble(1, maxComm);

			// 3 + 4. Execute the static Query + Process the result
			System.out.println("Part 1 - Static Query");
			ResultSet rs = myStmt.executeQuery(query);
			while (rs.next()) {
				String sid = rs.getString("sid");
				String fname = rs.getString("fname");
				String sname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String city = rs.getString("city");
				double commision = rs.getDouble("commission");

				System.out.println(sid + "," + fname + "," + sname + "," + dob + "," + city + "," + commision);
			}

			// (part 2) Execute the dynamic query + Process the result
			System.out.println("\nPart 2 - dynamic query");
			rs = myPrepStmt.executeQuery();
			while (rs.next()) {
				String sid = rs.getString("sid");
				String fname = rs.getString("fname");
				double commision = rs.getDouble("commission");

				System.out.println(sid + "," + fname + "," + commision);
			}

			// 5. Close the connection
			conn.close();
			myStmt.close();
			rs.close();
			scanner.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
