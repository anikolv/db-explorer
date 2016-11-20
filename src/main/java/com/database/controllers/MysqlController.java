package com.database.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.database.services.MysqlService;

@Controller
@RequestMapping("/mysql")
public class MysqlController {

	@Autowired
	MysqlService mysqlService;

	@RequestMapping("/schemas")
	public String getSchemas(Model model) {
		try {
			List<String> schemas = mysqlService.getAllSchemas();
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
			Map<String, List<String>>  tables = mysqlService.getTablesMetadataBySchema(schemaName);
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
			Map<String, List<String>> columnToValuesMap = mysqlService.getTableData(tableName, schemaName);
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
