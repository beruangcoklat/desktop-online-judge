package model;

import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.ResultSet;

import utility.Connect;

public class Problem {

	private int id;
	private String title, description, answer, input;

	public Vector<Submission> submissions() {
		Vector<Submission> list = new Vector<>();
		String query = String.format("select * from submissions where problem_id = %d", id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			while (rs.next()) {
				Submission s = new Submission();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String inputan) {
		this.input = inputan;
	}

}
