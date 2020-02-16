package controller;

import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.ResultSet;

import model.Problem;
import utility.Connect;

public class ProblemController {

	private static ProblemController instance = new ProblemController();

	private ProblemController() {

	}

	public static ProblemController getInstance() {
		return instance;
	}

	public Vector<Problem> all() {
		Vector<Problem> problems = new Vector<>();
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery("select * from problems");
		try {
			while (rs.next()) {
				Problem p = new Problem();
				p.setId(rs.getInt("id"));
				p.setDescription(rs.getString("description"));
				p.setAnswer(rs.getString("answer"));
				p.setTitle(rs.getString("title"));
				p.setInput(rs.getString("input"));
				problems.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return problems;
	}

	public Problem find(int id) {
		String query = String.format("select * from problems where id = %d", id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			if (rs.next()) {
				Problem p = new Problem();
				p.setAnswer(rs.getString("answer"));
				p.setDescription(rs.getString("description"));
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				p.setInput(rs.getString("input"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
