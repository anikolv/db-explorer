package com.database.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
	
	public Connection getConnection(String database) throws SQLException;

}
