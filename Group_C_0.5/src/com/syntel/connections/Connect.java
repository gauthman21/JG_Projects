package com.syntel.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", "system",
					"syntel123$");
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
