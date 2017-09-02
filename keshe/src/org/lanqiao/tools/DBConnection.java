package org.lanqiao.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	public Connection getCon(){
		Connection con=null;
		String url="jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=Text";
		String user="sa";
		String password="123";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
