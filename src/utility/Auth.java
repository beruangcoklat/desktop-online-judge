package utility;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import model.User;

public class Auth {

	private User activeUser;
	private static Auth instance = new Auth();

	public static Auth getInstance() {
		return instance;
	}

	public User user() {
		return activeUser;
	}

	public boolean attempt(String username, String password) {
		String query = String.format("select * from users where username = '%s' and password = '%s'", username,
				password);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				activeUser = new User(id, username, password);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void logout() {
		activeUser = null;
	}

}
