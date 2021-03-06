package com.database.factory;

import com.database.services.ConnectionManager;
import com.database.services.MySqlConnectionManagerImpl;
import com.database.services.PostgreSqlConnectionManagerImpl;

public final class ConnectionManagerFactory {
	
	private ConnectionManagerFactory() {}
	
	public static ConnectionManager getConnectionManager(String sqlServer) {
		if (sqlServer.equals("mysql")) {
			return new MySqlConnectionManagerImpl();
		} else if (sqlServer.equals("postgresql")) {
			return new PostgreSqlConnectionManagerImpl();
		}
		return null;
	}

}
