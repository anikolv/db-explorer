package com.database.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.database.builders.JdbcConnectionBuilder;

public class PostgreSqlConnectionManagerImpl implements ConnectionManager {
	
	private String url = "jdbc:postgresql://localhost/";
	private String username = "bss";
	private String password = "RN123uTa";

	@Override
	public Connection getConnection(String database) throws SQLException {
		return new JdbcConnectionBuilder(url + (database.isEmpty() ? "bss_voo_demo" : database))
				.driverClassName("org.postgresql.Driver")
				.username(username)
				.password(password)
				.build();
	}

}
