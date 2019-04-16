package com.database.services;

public class ConnectionTypeHolder {
	private static ConnectionTypeHolder INSTANCE = null;
	private String connectionType;
	private ConnectionTypeHolder() {}
	
	 public static ConnectionTypeHolder getInstance() {
	        if (INSTANCE == null) {
	            synchronized (ConnectionTypeHolder.class) {
	                if (INSTANCE == null) {
	                	INSTANCE = new ConnectionTypeHolder();
	                }
	            }
	        }
	        return INSTANCE;
	    }

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	
}
