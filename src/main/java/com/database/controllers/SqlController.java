package com.database.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.database.services.ConnectionTypeHolder;
import com.database.services.SqlService;

@Controller
@RequestMapping("/sql")
public class SqlController {
	
	/* proxy and singleton patterns */

	@Autowired
	private SqlService sqlServiceProxy;

	private ConnectionTypeHolder connectionTypeHolder = ConnectionTypeHolder.getInstance();

	@GetMapping("/schemas/{connectionType}")
	public String getSchemas(@PathVariable("connectionType") String connectionType, Model model) throws SQLException {
		connectionTypeHolder.setConnectionType(connectionType);
		List<String> schemas = sqlServiceProxy.getAllSchemas();
		model.addAttribute("schemas", schemas);
		return "schemas";
	}

	@GetMapping("/{schemaName}")
	public String openSchema(@PathVariable("schemaName") String schemaName, Model model) throws SQLException {
		Map<String, List<String>> tables = sqlServiceProxy.getTablesMetadataBySchema(schemaName);
		model.addAttribute("schema", schemaName);
		model.addAttribute("tables", tables);
		return "tables";
	}

	@GetMapping("/{schemaName}/{tableName}")
	public String openTable(@PathVariable("schemaName") String schemaName, @PathVariable("tableName") String tableName,
			Model model) throws SQLException {
		Map<String, List<String>> columnToValuesMap = sqlServiceProxy.getTableData(tableName, schemaName);
		model.addAttribute("schema", schemaName);
		model.addAttribute("table", tableName);
		model.addAttribute("columnNameToValuesMap", columnToValuesMap);
		return "table-content";
	}

	@ExceptionHandler(SQLException.class)
	private void handleSqlException(SQLException sqlException) throws Exception {
		System.out.println(sqlException.getMessage());
	}

}
