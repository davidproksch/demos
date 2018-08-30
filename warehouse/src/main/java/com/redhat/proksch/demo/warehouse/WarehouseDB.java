package com.redhat.proksch.demo.warehouse;

public class WarehouseDB {

        private String mysql_user = "not set";
        private String mysql_password = "not set";
        private String mysql_database = "not set";
        private String mysql_service = "not set";
        private String mysql_port = "not set";

	WarehouseDB() {
	    mysql_user = System.getenv("mysql_user");
	    mysql_password = System.getenv("mysql_password");
	    mysql_database = System.getenv("mysql_database");
	    mysql_service = System.getenv("mysql_service");
	    mysql_port = System.getenv("mysql_port");
	}

	public String getAllWarehouses() {
		return new String("All Warehouses!");
	}

}
