package com.redhat.proksch.demo.warehouse;

public class WarehouseDB {

        private String mysql_user = "not set";

	WarehouseDB() {
	    mysql_user = System.getenv("mysql_user");
	}

	public String getMysqlUser() { return mysql_user; }

}
