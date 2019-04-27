package com.database.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionManagerImpl implements ConnectionManager {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String username = "";
	private String password = "";
	private String database = "";

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url + database, username, password);
	}
}
