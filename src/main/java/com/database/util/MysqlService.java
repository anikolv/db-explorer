package com.database.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MysqlService {

	@Value("${mysqlProperties.url}")
	private String url;
	
	@Value("${mysqlProperties.username}")
	private String username;
	
	@Value("${mysqlProperties.password}")
	private String password;

	private Connection getConnection(String database) throws SQLException {
		return DriverManager.getConnection(url + database, username, password);
	}

	public List<String> getAllSchemas() throws SQLException {
		List<String> schemas = new ArrayList<String>();
		Connection connection = getConnection("");
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet catalogs = meta.getCatalogs();
		while (catalogs.next()) {
			schemas.add(catalogs.getString(1));
		}
		return schemas;
	}

}
