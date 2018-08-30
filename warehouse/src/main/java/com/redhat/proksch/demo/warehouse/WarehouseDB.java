package com.redhat.proksch.demo.warehouse;

public class WarehouseDB {

        private String mysql_user = "not set";
        private String mysql_password = "not set";
        private String mysql_database = "not set";
        private String mysql_service = "not set";
        private String mysql_port = "not set";
	private String mysql_url = "not set";
	private StringBuffer message = null;
        private boolean connGood = false;

	WarehouseDB() {
	    mysql_user = System.getenv("mysql_user");
	    mysql_password = System.getenv("mysql_password");
	    mysql_database = System.getenv("mysql_database");
	    mysql_service = System.getenv("mysql_service");
	    mysql_port = System.getenv("mysql_port");

            mysql_url = new StringBuffer("jdbc:mysql://")
		.append(mysql_service.trim()).append("/")
		.append(mysql_database.trim()).toString();

	    try {
	    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
		connGood = true;
	    }
	    catch (Exception e) {
		message = new StringBuffer(e.getMessage());
	    }

	}

	public String getAllWarehouses() {
		if (connGood)
			return new String("All Warehouses!");
		else
			return message.toString();
	}

}
