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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.database.factory.ConnectionManagerFactory;

@Service("sqlService")
public class SqlServiceImpl implements SqlService {
	
	@Autowired
	private ConnectionTypeHolder connectionTypeHolder;
	
	private String connectionType;

	@Value("${sql.query.all}")
	private String selectAllQuery;

	@Value("${sql.query.information}")
	private String selectInfoQuery;

	@Value("${sql.query.information.order}")
	private String selectInfoQueryOrder;
	
	@PostConstruct
	public void init() {
		connectionType = connectionTypeHolder.getConnectionType();
	}
	
	@Override
	public List<String> getAllSchemas() throws SQLException {
		
		List<String> schemas = new ArrayList<String>();
		Connection connection = ConnectionManagerFactory.getConnectionManager(connectionType).getConnection("");

		DatabaseMetaData meta = connection.getMetaData();
		ResultSet catalogs = meta.getCatalogs();
		while (catalogs.next()) {
			schemas.add(catalogs.getString(1));
		}
		return schemas;
	}


	@Override
	public Map<String, List<String>> getTablesMetadataBySchema(String schemaName) throws SQLException {

		String sqlQuery = selectInfoQuery + schemaName + selectInfoQueryOrder;
		Map<String, List<String>> columnNameToValuesMap = composeMapFromResultSet(sqlQuery, schemaName);
		return columnNameToValuesMap;

	}

	@Override
	public Map<String, List<String>> getTableData(String tableName, String schemaName) throws SQLException {

		String sqlQuery = selectAllQuery + tableName;
		Map<String, List<String>> columnNameToValuesMap = composeMapFromResultSet(sqlQuery, schemaName);
		return columnNameToValuesMap;

	}

	private Map<String, List<String>> composeMapFromResultSet(String sqlQuery, String schemaName) throws SQLException {
		
		Connection connection = ConnectionManagerFactory.getConnectionManager(connectionType).getConnection(schemaName);
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
