package com.database.services;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	public List<String> getTablesFor(String schemaName) throws SQLException {
		List<String> tables = new ArrayList<String>();
		Connection connection = getConnection(schemaName);
		DatabaseMetaData meta = connection.getMetaData();
		String[] types = { "TABLE" };
		ResultSet tableNames = meta.getTables(null, null, "%", types);
		while (tableNames.next()) {
			tables.add(tableNames.getString("TABLE_NAME"));
		}
		return tables;
	}

	public Map<String, List<String>> getContentOf(String tableName, String schemaName) throws SQLException {

		Connection connection = getConnection(schemaName);
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM " + tableName;
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		List<String> columnNames = new LinkedList<>();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(rsmd.getColumnName(i));
		}

		Map<String, List<String>> columnNameToValuesMap = new HashMap<String, List<String>>();

		for (String columnName : columnNames) {
			List<String> values = new ArrayList<>();
			try {
				if (!rs.next()) {
					rs.close();
					rs = stmt.executeQuery(sql);
				}
				while (rs.next()) {
					values.add(rs.getString(columnName));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			columnNameToValuesMap.put(columnName, values);
		}
		rs.close();

		return columnNameToValuesMap;
	}

}
