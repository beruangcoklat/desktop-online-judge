package controller;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import model.User;
import utility.Connect;

public class UserController {

	private static UserController instance = new UserController();

	private UserController() {

	}

	public static UserController getInstance() {
		return instance;
	}

	public boolean register(String username, String password) {
		if (findUsername(username))
			return false;
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.save();
		return true;
	}

	private boolean findUsername(String username) {
		String query = String.format("select * from users where username = '%s'", username);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User find(int id) {
		String query = String.format("select * from users where id = %d", id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			if (rs.next()) {
				User u = new User();
				u.setId(id);
				u.setPassword(rs.getString("username"));
				u.setUsername(rs.getString("password"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
