package com.database.controllers;

import java.sql.SQLException;
import java.util.List;

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
	
	@RequestMapping("/schema/{schemaName}")
	public String openSchema(@PathVariable("schemaName") String schemaName, Model model) {
		try {
			List<String> tables = mysqlService.getTablesFor(schemaName);
			model.addAttribute("tables", tables);
			return "tables";
		} catch (SQLException e) {
			model.addAttribute("message", e.getMessage());
			return "exception";		
		}
	}

}
