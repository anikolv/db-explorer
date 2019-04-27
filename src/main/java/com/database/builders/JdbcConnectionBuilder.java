package com.database.builders;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcConnectionBuilder {

	private String driverClassName;
	private String url;
	private String username;
	private String password;

	public JdbcConnectionBuilder(String url) {
		this.url = url;
	}

	public JdbcConnectionBuilder driverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
		return this;
	}

	public JdbcConnectionBuilder username(String username) {
		this.username = username;
		return this;
	}

	public JdbcConnectionBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public Connection build() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource.getConnection();
	}

}
