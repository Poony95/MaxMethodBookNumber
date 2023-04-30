package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionProvider {

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
	}
	public static void close2(Statement stmt, Connection conn) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생 : " +e.getMessage());
		}
	}
	public static Connection getconnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url, "c##madang", "madang");
		} catch (Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
		return conn;
	}
}
