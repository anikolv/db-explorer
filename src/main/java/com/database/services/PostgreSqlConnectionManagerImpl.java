package com.database.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSqlConnectionManagerImpl implements ConnectionManager {
	
	private String url = "jdbc:postgresql://localhost/";
	private String username = "bss";
	private String password = "RN123uTa";

	@Override
	public Connection getConnection(String database) throws SQLException {
		return DriverManager.getConnection(url + database, username, password);
	}

}
