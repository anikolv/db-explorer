package com.database.services;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.database.factory.ConnectionManagerFactory;

@Service("sqlService")
public class SqlServiceImpl implements SqlService {
	
	/* factory, strategy, builder patterns */
	
	private ConnectionTypeHolder connectionTypeHolder = ConnectionTypeHolder.getInstance();
	
	@Override
	public List<String> getAllSchemas() throws SQLException {
		
		List<String> schemas = new ArrayList<String>();
		Connection connection = ConnectionManagerFactory.getConnectionManager(connectionTypeHolder.getConnectionType())
				.getConnection("");

		DatabaseMetaData meta = connection.getMetaData();
		ResultSet catalogs = connectionTypeHolder.getConnectionType().equals("mysql") ? meta.getCatalogs()
				: meta.getSchemas();
		while (catalogs.next()) {
			schemas.add(catalogs.getString(1));
		}
		catalogs.close();
		return schemas;
	}


	@Override
	public Map<String, List<String>> getTablesMetadataBySchema(String schemaName) throws SQLException {

		String sqlQuery = "SELECT * "
						+ "FROM information_schema.tables "
						+ "WHERE table_schema='" + schemaName + "' "
						+ "ORDER BY table_name";
		Map<String, List<String>> columnNameToValuesMap = composeMapFromResultSet(sqlQuery, schemaName);
		return columnNameToValuesMap;

	}

	@Override
	public Map<String, List<String>> getTableData(String tableName, String schemaName) throws SQLException {

		String sqlQuery = "SELECT * "
						+ "FROM " + tableName;
		Map<String, List<String>> columnNameToValuesMap = composeMapFromResultSet(sqlQuery, schemaName);
		return columnNameToValuesMap;

	}

	private Map<String, List<String>> composeMapFromResultSet(String sqlQuery, String schemaName) throws SQLException {
		
		Connection connection = ConnectionManagerFactory.getConnectionManager(connectionTypeHolder.getConnectionType()).getConnection("");
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		List<String> columnNames = new LinkedList<>();
		Map<String, List<String>> columnNameToValuesMap = new HashMap<String, List<String>>();

		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsmd.getColumnName(i);
			columnNames.add(rsmd.getColumnName(i));
			columnNameToValuesMap.put(columnName, new ArrayList<String>());
		}

		try {
			while (rs.next()) {
				for (String columnName : columnNames) {
					List<String> columnDataList = columnNameToValuesMap.get(columnName);
					columnDataList.add(rs.getString(columnName));
					columnNameToValuesMap.put(columnName, columnDataList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnNameToValuesMap;

	}
}
