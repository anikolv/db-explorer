package com.database.application.proxies;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.services.SqlService;

@Service("sqlServiceProxy")
public class SqlServiceProxy implements SqlService {
	
	@Autowired
	private SqlService sqlService;

	@Override
	public List<String> getAllSchemas() throws SQLException {
		System.out.println("Get all schemas start");
		List<String> schemas = sqlService.getAllSchemas();
		System.out.println("Get all schemas end");
		
		return schemas;
	}

	@Override
	public Map<String, List<String>> getTablesMetadataBySchema(String schemaName) throws SQLException {
		System.out.println("Get tables metadata by schema start");
		Map<String, List<String>>  tables = sqlService.getTablesMetadataBySchema(schemaName);
		System.out.println("Get tables metadata by schema end");
		
		return tables;
	}

	@Override
	public Map<String, List<String>> getTableData(String tableName, String schemaName) throws SQLException {
		System.out.println("Get table data");
		Map<String, List<String>> columnToValuesMap = sqlService.getTableData(tableName, schemaName);
		System.out.println("Get table data");
		
		return columnToValuesMap;
	}

}
