package com.database.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MysqlService {

	/**
	 * @return
	 * @throws SQLException
	 */
	List<String> getAllSchemas() throws SQLException;

	/**
	 * @param schemaName
	 * @return
	 * @throws SQLException
	 */
	Map<String, List<String>> getTablesMetadataBySchema(String schemaName) throws SQLException;

	/**
	 * @param tableName
	 * @param schemaName
	 * @return
	 * @throws SQLException
	 */
	Map<String, List<String>> getTableData(String tableName, String schemaName) throws SQLException;

}