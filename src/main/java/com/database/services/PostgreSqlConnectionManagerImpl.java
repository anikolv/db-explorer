package com.database.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.database.builders.JdbcConnectionBuilder;

public class PostgreSqlConnectionManagerImpl implements ConnectionManager {
	
	private String url = "jdbc:postgresql://localhost/";
	private String username = "";
	private String password = "";
	private String database = "";

	@Override
	public Connection getConnection() throws SQLException {
		return new JdbcConnectionBuilder(url + database)
				.driverClassName("org.postgresql.Driver")
				.username(username)
				.password(password)
				.build();
	}

}
