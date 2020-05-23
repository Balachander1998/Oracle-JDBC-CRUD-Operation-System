package com.syncfusion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager connectionManager=null;
	private final String DB_URL="jdbc:oracle:thin:@localhost:1521:orcl";
	private final String USERNAME="balachander";
	private final String PASSWORD="cool";
	private final String ORACLE_DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	private Connection connection;
	
	private ConnectionManager() {
		
	}
	public static ConnectionManager getInstance() {
		if(connectionManager==null) {
			connectionManager=new ConnectionManager();
		}
		return connectionManager;
	}
	public Connection getConnectionDetails() {
		try {
			Class.forName(ORACLE_DRIVER_NAME);
			connection=DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			if(connection!=null) {
				System.out.println("Connection Opened");
			}
			else {
				System.out.println("Connection Closed");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
