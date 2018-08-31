package com.redhat.proksch.demo.ui;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NewWarehouseRest {

        public void getAllWarehouses() {
		String warehouseService = System.getenv("warehouse_rest_svc");
		String warehousePath = System.getenv("warehouse_rest_path");
		System.out.println("---------> " + warehouseService);
		System.out.println("---------> " + warehousePath);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(warehouseService).path(warehousePath);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		String j = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<String> warehouses;
		try {
			warehouses = mapper.readValue(j, List.class);
			warehouses.forEach(w -> {
				System.out.println(w);
			});
		}
		catch (Exception e) {
			warehouses = new ArrayList();
			warehouses.add("Something went WRONG!!!!!");
			warehouses.add(e.getMessage());
		}
		System.out.println(warehouses);
        }


}
