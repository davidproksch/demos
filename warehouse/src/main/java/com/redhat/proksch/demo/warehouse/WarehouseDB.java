package com.redhat.proksch.demo.warehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WarehouseDB {

        private String mysql_user = "not set";
        private String mysql_password = "not set";
        private String mysql_database = "not set";
        private String mysql_service = "not set";
        private String mysql_port = "not set";
	private String mysql_url = "not set";
	private StringBuffer message = null;
        private boolean connGood = false;
        private Connection conn = null;
	private PreparedStatement allWH = null;
	private PreparedStatement oneWH = null;

	WarehouseDB() {
	    mysql_user = System.getenv("mysql_user");
	    mysql_password = System.getenv("mysql_password");
	    mysql_database = System.getenv("mysql_database");
	    mysql_service = System.getenv("mysql_service");
	    mysql_port = System.getenv("mysql_port");

            mysql_url = new StringBuffer("jdbc:mysql://")
		.append(mysql_service.trim()).append(":")
		.append(mysql_port.trim())
		.append("/")
		.append(mysql_database.trim()).toString();

	    System.err.println(mysql_url);

	    try {
	    	Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
		conn = DriverManager.getConnection (mysql_url, 
			mysql_user.trim(), mysql_password.trim());

		allWH = conn.prepareStatement("select count(*) from warehouse");

		connGood = true;
	    }
	    catch (Exception e) {
		message = new StringBuffer(e.getMessage());
	    }

	}

	public String getAllWarehouses() {
		if (connGood) {
			StringBuffer sb = new StringBuffer();
			try {
		System.err.println(conn.isValid(10));
			ResultSet rs = allWH.executeQuery();
			long c = 1; //rs.getLong(0);
			sb = sb.append("Count: ")
				.append(c);
			}
			catch (Exception e) {
				sb = sb.append("Error -> ")
					.append(e.getMessage());
			}	
			return sb.toString();
		}
		else
			return message.toString();
	}

}
