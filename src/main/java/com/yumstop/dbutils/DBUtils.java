package com.yumstop.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class DBUtils {
	private static String url="jdbc:mysql://localhost:3306/tapfoods";
	private static String name="root";
	private static String pwd="root";
	private static Connection con;

	public static Connection getConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,name,pwd);
		}
		
		catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return con;
	}
}
