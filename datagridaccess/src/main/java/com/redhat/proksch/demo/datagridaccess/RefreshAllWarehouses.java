package com.redhat.proksch.demo.datagridaccess;

import org.springframework.http.converter.json.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;;
import org.springframework.http.HttpEntity;;
import org.springframework.http.HttpMethod;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.*;

@RestController
public class RefreshAllWarehouses {

	@PutMapping("/refresh")
	public String refreshAllWH() {
		boolean rc = false;
		rc = refreshCache(getWarehouseList());
		
		if (rc) return "Good";
		else return "Bad";	
	}

	private boolean refreshCache(List<Warehouse> ws) {
		boolean rc = false;
		StringBuffer url = new StringBuffer("http://datagrid-app-mbodemo.6923.rh-us-east-1.openshiftapps.com");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(setMessageConverters());

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Warehouse> entity; //= new HttpEntity<>("parameters",headers);
		for (Warehouse w: ws) {
			entity = new HttpEntity<Warehouse>(w, headers);
			// First Delete
			// Then Add
			StringBuffer u = new StringBuffer(url).append("/rest/warehouses/").append(w.getId());
			System.err.println(u.toString());
			restTemplate.exchange(u.toString(), HttpMethod.POST, 
				entity, Warehouse.class);
			System.out.println(w);
		}
		
		return rc;
	}

	private MappingJackson2HttpMessageConverter setMessageConverters() {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                List<MediaType> mediaTypes = new ArrayList<MediaType>();
                mediaTypes.add(MediaType.APPLICATION_JSON);
                mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
		return mappingJackson2HttpMessageConverter;
	}


	private List<Warehouse> getWarehouseList() { 
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://warehouse-00-mbodemo.b9ad.pro-us-east-1.openshiftapps.com/all";
		restTemplate.getMessageConverters().add(setMessageConverters());
		ResponseEntity responseEntity = restTemplate.getForEntity(url,
			Warehouse[].class);
		
		Warehouse[] ws = (Warehouse[])responseEntity.getBody();
		//List<Warehouse> wa = new ArrayList<Warehouse>(Arrays.asList(ws));
		return new ArrayList<Warehouse>(Arrays.asList(ws));

	}

}
