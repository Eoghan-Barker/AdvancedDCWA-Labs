package lab1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Q3 {

	public static void main(String[] args) {
		try {
			// Establish Connection
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://localhost:3306/employee_kin");
			mysqlDS.setUser("root");
			mysqlDS.setPassword("");
			Connection conn = mysqlDS.getConnection();
	
			// Static Query
			// Create Statement
			Statement myStmt = conn.createStatement();
			String query = "SELECT * FROM salesperson_table";

			// Execute Query and Process Result
			ResultSet rs = myStmt.executeQuery(query);
			while (rs.next()) {
				String sid = rs.getString("sid");

				System.out.println(sid);
			}

			// Dynamic Query
			// Create PreparedStatement
			String prepQuery = "DELETE FROM salesperson_city_table WHERE city = ?";
			PreparedStatement myPrepStmt = conn.prepareStatement(prepQuery);
			myPrepStmt.setString(1, prepQuery);

			int deleteResult = myPrepStmt.executeUpdate();
			System.out.println(deleteResult + " rows deleted");

			// Close the connection
			conn.close();
			myStmt.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}