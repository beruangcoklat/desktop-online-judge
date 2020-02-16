package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Connect {

	private final String DB = "prk", USERNAME = "root", PASSWORD = "";
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	private static Connect instance = new Connect();

	public static Connect getInstance() {
		return instance;
	}

	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + DB, USERNAME, PASSWORD);
			stmt = con.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}

		return rs;
	}

	public void executeUpdate(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public PreparedStatement executePreparedStatement(String query) {
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			return ps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}