package com.database.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.database.services.ConnectionTypeHolder;
import com.database.services.SqlService;

@Controller
@RequestMapping("/sql")
public class SqlController {

	@Autowired
	private SqlService sqlServiceProxy;
	
	@Autowired
	private ConnectionTypeHolder connectionTypeHolder;

	@RequestMapping("/schemas/{sqlServer}")
	public String getSchemas(@PathVariable("sqlServer") String sqlServer, Model model) {
		try {
			connectionTypeHolder.setConnectionType(sqlServer);
			List<String> schemas = sqlServiceProxy.getAllSchemas();
			model.addAttribute("schemas", schemas);
			return "schemas";
		} catch (SQLException e) {
			model.addAttribute("message", e.getMessage());
			return "exception";
		}
	}

	@RequestMapping("/{schemaName}")
	public String openSchema(@PathVariable("schemaName") String schemaName, Model model) {
		try {
			Map<String, List<String>>  tables = sqlServiceProxy.getTablesMetadataBySchema(schemaName);
			model.addAttribute("schema", schemaName);
			model.addAttribute("tables", tables);
			return "tables";
		} catch (SQLException e) {
			model.addAttribute("message", e.getMessage());
			return "exception";
		}
	}

	@RequestMapping("/{schemaName}/{tableName}")
	public String openTable(@PathVariable("schemaName") String schemaName,
			@PathVariable("tableName") String tableName, Model model) {
		try {
			Map<String, List<String>> columnToValuesMap = sqlServiceProxy.getTableData(tableName, schemaName);
			model.addAttribute("schema", schemaName);
			model.addAttribute("table", tableName);
			model.addAttribute("columnNameToValuesMap", columnToValuesMap);
			return "table-content";
		} catch (SQLException e) {
			model.addAttribute("message", e.getMessage());
			return "exception";
		}
	}

}
