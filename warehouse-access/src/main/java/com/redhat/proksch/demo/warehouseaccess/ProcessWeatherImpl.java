package com.redhat.proksch.demo.warehouseaccess;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;


import org.springframework.http.HttpStatus;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.StringWriter;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Service("warehousesService")
public class ProcessWeatherImpl implements ProcessWeather {

    private String dataCacheHost;
    private String dataCachePort;


    @Override
    public String getWarehouses( String warehouses ) {
	StringWriter sw = new StringWriter();
	ObjectMapper mapper = new ObjectMapper();
    List<Map<String,String>> w;
	try {
		w = mapper.readValue(warehouses, List.class);
		w.forEach(ww -> {
				String zip = ww.get("zip");
                Weather weather = augmentWithWeather(zip);
			ww.put("weather",weather.getWeather());
                        });
		mapper.writeValue(sw, w);
	}
	catch (Exception e) {
		System.err.println(e.getMessage());
	}
        return new String( sw.toString() );
        //return new String( warehouses );
    }

    private Weather augmentWithWeather(String postalCode) {
		dataCacheHost = System.getenv("data_cache_host");
		dataCachePort = System.getenv("data_cache_port");
		RestTemplate restTemplate = new RestTemplate();
	        StringBuffer baseURL = new StringBuffer("http://")
			.append(dataCacheHost).append(":")
			.append(dataCachePort).append("/");
		StringBuffer cacheURL = new StringBuffer(baseURL)
			.append("/weather/cache?zip=")
			.append(postalCode.trim());
		StringBuffer remoteURL = new StringBuffer(baseURL)
			.append("/weather/remote?zip=")
			.append(postalCode.trim());

	    // Check and see if the weather for this postal code
 	    // has already been fetched and is stored in the Data Grid
		restTemplate.getMessageConverters().add(setMessageConverters());
                ResponseEntity responseEntity;
		Weather weather = null;
		try {
			responseEntity = restTemplate.getForEntity(cacheURL.toString(), Weather.class);
			weather = (Weather)responseEntity.getBody();

		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}

		if (! weather.getFound() ) {
			try {
                        	responseEntity = restTemplate.getForEntity(remoteURL.toString(), Weather.class);
                        	weather = (Weather)responseEntity.getBody();

                	}
                	catch (Exception e) {
                       	 System.err.println(e.getMessage());
                	}
		}
		

		return weather;
    }

	private RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

        private MappingJackson2HttpMessageConverter setMessageConverters() {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                List<MediaType> mediaTypes = new ArrayList<MediaType>();
                mediaTypes.add(MediaType.TEXT_HTML);
                mediaTypes.add(MediaType.APPLICATION_JSON);
                mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
                return mappingJackson2HttpMessageConverter;
        }

}
