package controller;

import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.ResultSet;

import model.Post;
import utility.Auth;
import utility.Connect;

public class PostController {

	private static PostController instance = new PostController();

	private PostController() {

	}

	public static PostController getInstance() {
		return instance;
	}

	public Vector<Post> getFromProblem(int problem_id) {
		Vector<Post> posts = new Vector<>();
		String query = String.format("select * from posts where problem_id = %d", problem_id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			while (rs.next()) {
				Post p = new Post();
				p.setDescription(rs.getString("description"));
				p.setId(rs.getInt("id"));
				p.setUser_id(rs.getInt("user_id"));
				p.setProblem_id(rs.getInt("problem_id"));
				posts.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	public void insert(String post, int problem_id) {
		int user_id = Auth.getInstance().user().getId();
		String query = String.format(
				"INSERT INTO `posts`(`description`, `user_id`, `problem_id`) VALUES ('%s', %d, %d)", post, user_id,
				problem_id);
		Connect.getInstance().executeUpdate(query);
	}

}
