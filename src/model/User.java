package model;

import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import utility.Connect;

public class User {

	private int id;
	private String username, password;

	public Vector<Submission> getSubmissions() {
		Vector<Submission> list = new Vector<>();
		String query = String.format("select * from submissions where user_id = %d", id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			while (rs.next()) {
				Submission s = new Submission();
				s.setId(rs.getInt("id"));
				s.setProblem_id(rs.getInt("problem_id"));
				s.setStatus_id(rs.getInt("status_id"));
				s.setSubmission(rs.getString("submission"));
				s.setUser_id(rs.getInt("user_id"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User() {

	}

	public void save() {
		String query = "insert into users values(NULL, ?, ?)";
		PreparedStatement ps = Connect.getInstance().executePreparedStatement(query);
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
