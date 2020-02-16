package controller;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import model.Submission;
import utility.Connect;

public class SubmissionController {

	private static SubmissionController instance = new SubmissionController();

	private SubmissionController() {

	}

	public static SubmissionController getInstance() {
		return instance;
	}

	public void insert(int user_id, int problem_id, int status_id, String submission) {
		Submission s = new Submission();
		s.setUser_id(user_id);
		s.setProblem_id(problem_id);
		s.setStatus_id(status_id);
		s.setSubmission(submission);
		s.save();
	}

	public Submission find(int id) {
		String query = String.format("select * from submissions where id = %d", id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			if (rs.next()) {
				Submission s = new Submission();
				s.setId(id);
				s.setProblem_id(rs.getInt("problem_id"));
				s.setStatus_id(rs.getInt("status_id"));
				s.setUser_id(rs.getInt("user_id"));
				s.setSubmission(rs.getString("submission"));
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
