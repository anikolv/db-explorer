package com.database.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.util.MysqlService;


@RestController
public class TestController {
	
	@Autowired
	MysqlService mysqlService;

	@RequestMapping("/getSchemas")
	public List<String> getSchemas() {
		try {
			return mysqlService.getAllSchemas();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}

}
