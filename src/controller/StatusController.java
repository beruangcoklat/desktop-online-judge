package controller;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import model.Status;
import utility.Connect;

public class StatusController {

	private static StatusController instance = new StatusController();

	private StatusController() {

	}

	public static StatusController getInstance() {
		return instance;
	}

	public Status find(int id) {
		String query = String.format("select * from statuses where id = '%d'", id);
		ResultSet rs = (ResultSet) Connect.getInstance().executeQuery(query);
		try {
			if (rs.next()) {
				Status s = new Status();
				s.setId(id);
				s.setName(rs.getString("name"));
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
