package com.redhat.proksch.demo.ui;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class NewWarehouseRest {

        public void getAllWarehouses() {
		String warehouseService = System.getenv("warehouse_rest_svc");
		String warehousePath = System.getenv("warehouse_rest_path");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(warehouseService).path(warehousePath);
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
		System.out.println(response.readEntity());
        }


}
