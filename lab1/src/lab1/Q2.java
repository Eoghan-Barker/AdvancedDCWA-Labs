package lab1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Q2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {
			// Establish Connection
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://localhost:3306/salespersonsDB2P1");
			mysqlDS.setUser("root");
			mysqlDS.setPassword("");
			Connection conn = mysqlDS.getConnection();

			// Part 1
			System.out.println("Part 1");
			// Create Statement
			Statement myStmt = conn.createStatement();
			String query = "SELECT s.sid, s.fname, s.surname, t.city, t.commission FROM salesperson_table s LEFT JOIN salesperson_city_table t ON s.sid = t.sid";

			// Execute Query and Process Result
			ResultSet rs = myStmt.executeQuery(query);
			while (rs.next()) {
				String sid = rs.getString("sid");
				String fname = rs.getString("fname");
				String sname = rs.getString("surname");
				String city = rs.getString("city");
				double commision = rs.getDouble("commission");

				System.out.println(sid + "," + fname + "," + sname + "," + city + "," + commision);
			}

			// Part 2
			System.out.println("\nPart 2");
			String prepQuery = "DELETE FROM salesperson_city_table WHERE city = ?";
			PreparedStatement myPrepStmt = conn.prepareStatement(prepQuery);
			System.out.println("Enter city for deletion:");
			String deleteCity = input.nextLine();
			myPrepStmt.setString(1, deleteCity);

			int deleteResult = myPrepStmt.executeUpdate();
			System.out.println(deleteResult + " rows deleted");

			// Part 3
			System.out.println("\nPart 3");
			System.out.println("Enter details for new salesperson:");
			System.out.println("sid: ");
			String newSID = input.nextLine();
			System.out.println("First Name: ");
			String newFname = input.nextLine();
			System.out.println("Surname: ");
			String newSurname = input.nextLine();
			System.out.println("DOB in format YYYY-MM-DD: ");
			String newDOB = input.nextLine();

			String prepQuery2 = "INSERT INTO salesperson_table VALUES(?, ?, ?, ?)";
			PreparedStatement myPrepStmt2 = conn.prepareStatement(prepQuery2);

			myPrepStmt2.setString(1, newSID);
			myPrepStmt2.setString(2, newFname);
			myPrepStmt2.setString(3, newSurname);
			myPrepStmt2.setString(4, newDOB);

			int updateResult = myPrepStmt2.executeUpdate();
			System.out.println(updateResult + " Row(s) Added");

			// Close the connection
			conn.close();
			myStmt.close();
			rs.close();
			input.close();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
