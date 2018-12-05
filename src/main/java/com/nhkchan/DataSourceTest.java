package main.java.com.nhkchan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DataSourceTest {

	public static void main(String[] args) {

		String sqlQuery = "SELECT PERSON_ID, FIRST_NAME, LAST_NAME FROM people";
		testDS(sqlQuery);
		
	}

	private static void testDS(String query) {
		DataSource ds = MyDataSourceFactory.getMySQLDataSource();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("Column 1 :" + rs.getInt(1));
				System.out.println("Column 2 :" + rs.getString(2));
				System.out.println("Column 3 :" + rs.getString(3));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
