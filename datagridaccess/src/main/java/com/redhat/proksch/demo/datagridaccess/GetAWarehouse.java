package com.redhat.proksch.demo.datagridaccess;

import org.springframework.http.converter.json.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;;
import org.springframework.http.HttpEntity;;
import org.springframework.http.HttpMethod;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.*;

@RestController
public class GetAWarehouse {

	private MappingJackson2HttpMessageConverter setMessageConverters() {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                List<MediaType> mediaTypes = new ArrayList<MediaType>();
                mediaTypes.add(MediaType.APPLICATION_JSON);
                mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
		return mappingJackson2HttpMessageConverter;
	}


	@GetMapping("/warehouse")
	public Warehouse getWarehouse(@RequestParam("id") String id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://datagrid-app-mbodemo.6923.rh-us-east-1.openshiftapps.com/rest/warehouses/" + id.trim();
		restTemplate.getMessageConverters().add(setMessageConverters());
		ResponseEntity responseEntity;
		Warehouse ws ;
		try { 
			responseEntity = restTemplate.getForEntity(url,
			Warehouse.class);
			ws = (Warehouse)responseEntity.getBody();
		}
		catch (HttpClientErrorException hce) {
			System.err.println(hce.getStatusCode());
			ws = null;
		}
		
		return ws;

	}

}
