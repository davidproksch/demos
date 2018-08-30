package com.redhat.proksch.demo.warehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;

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

		StringBuffer sb = new StringBuffer("select w_id, w_name, ")
			.append("w_street_1, w_city, w_state, w_zip ")
			.append("from warehouse order by w_id");
		allWH = conn.prepareStatement(sb.toString());

		sb = new StringBuffer("select w_id, w_name, ")
			.append("w_street_1, w_city, w_state, w_zip ")
			.append("from warehouse where w_id = ?");
		oneWH = conn.prepareStatement(sb.toString());

		connGood = true;
	    }
	    catch (Exception e) {
		message = new StringBuffer(e.getMessage());
	    }

	}

  	public String getOneWarehouse(String id) {
		int i_id = Integer.parseInt(id);
		if (connGood) {
           StringBuffer sb = new StringBuffer();
            List<Map<String,String>> l = new ArrayList<Map<String,String>>();
            Map<String,String> m = null;
            try {
                ResultSet rs = oneWH.executeQuery();
                while (rs.next()) {
                    m = new HashMap<String,String>();
                    m.put("id",new Integer(rs.getInt(1)).toString());
                    m.put("name",rs.getString(2));
                    m.put("street",rs.getString(3));
                    m.put("city",rs.getString(4));
                    m.put("state",rs.getString(5));
                    m.put("zip",rs.getString(6));
                    l.add(m);
                }
                ObjectWriter ow = new ObjectMapper().writer()
                    .withDefaultPrettyPrinter();
                sb = sb.append(ow.writeValueAsString(l));
                //sb = sb.append(l.toString());
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

	public String getAllWarehouses() {
		if (connGood) {
			StringBuffer sb = new StringBuffer();
			List<Map<String,String>> l = new ArrayList<Map<String,String>>();
			Map<String,String> m = null;
			try {
				ResultSet rs = allWH.executeQuery();
				while (rs.next()) {
					m = new HashMap<String,String>();
					m.put("id",new Integer(rs.getInt(1)).toString());
					m.put("name",rs.getString(2));
					m.put("street",rs.getString(3));
					m.put("city",rs.getString(4));
					m.put("state",rs.getString(5));
					m.put("zip",rs.getString(6));
					l.add(m);
				}
				ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
				sb = sb.append(ow.writeValueAsString(l));
				//sb = sb.append(l.toString());
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
