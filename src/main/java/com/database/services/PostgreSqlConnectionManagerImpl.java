package com.database.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PostgreSqlConnectionManagerImpl implements ConnectionManager {
	
	private String url = "jdbc:postgresql://localhost/";
	private String username = "bss";
	private String password = "RN123uTa";

	@Override
	public Connection getConnection(String database) throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(url + database);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource.getConnection();
	}

}
