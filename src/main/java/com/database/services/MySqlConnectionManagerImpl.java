package com.database.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionManagerImpl implements ConnectionManager {
	
	private String url = "jdbc:mysql://127.0.0.1:3306/";
	private String username = "root";
	private String password = "";

	@Override
	public Connection getConnection(String database) throws SQLException {
		return DriverManager.getConnection(url + database, username, password);
	}
}
