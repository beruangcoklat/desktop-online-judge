package model;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import utility.Connect;

public class Submission {

	private int id, user_id, problem_id, status_id;
	private String submission;

	public void save() {
		String query = "insert into submissions values(NULL, ?, ?, ?, ?)";
		PreparedStatement ps = Connect.getInstance().executePreparedStatement(query);
		try {
			ps.setInt(1, user_id);
			ps.setInt(2, problem_id);
			ps.setInt(3, status_id);
			ps.setString(4, submission);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProblem_id() {
		return problem_id;
	}

	public void setProblem_id(int problem_id) {
		this.problem_id = problem_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getSubmission() {
		return submission;
	}

	public void setSubmission(String submission) {
		this.submission = submission;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
