package com.redhat.proksch.demo.datagridaccess;

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
import com.fasterxml.jackson.databind.JsonNode ;
import com.fasterxml.jackson.core.type.TypeReference;


import javax.json.*;
import javax.json.stream.*;
import javax.json.stream.JsonParser.Event;
import org.glassfish.json.*;

import java.util.List;
import java.util.*;
import java.io.*;

@RestController
public class GetWeather{

	private MappingJackson2HttpMessageConverter setMessageConverters() {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                List<MediaType> mediaTypes = new ArrayList<MediaType>();
                mediaTypes.add(MediaType.TEXT_HTML);
                mediaTypes.add(MediaType.APPLICATION_JSON);
                mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
		return mappingJackson2HttpMessageConverter;
	}

	@Post("/weather/stash")
	public void getWeather(@RequestBody Weather weather) {
		System.err.println(weather);

	}
	@GetMapping("/weather/cache")
	public Weather getWeatherCache(@RequestParam("zip") String zip) {
		Weather w = new Weather();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://datagrid-app-mbodemo.6923.rh-us-east-1.openshiftapps.com/rest/weather/" + zip.trim();
                restTemplate.getMessageConverters().add(setMessageConverters());
                ResponseEntity responseEntity;
		String weatherString;
		try {
			responseEntity = restTemplate.getForEntity(url,
                        Weather.class);
                        w = (Weather)responseEntity.getBody();
			w.setFound(true);
		}	
		catch (HttpClientErrorException hcd) {
			HttpStatus hs = hcd.getStatusCode();
			if (hs == HttpStatus.NOT_FOUND)	{
				w.setFound(false);
				System.err.println("Not Found");
			}
			else { System.err.println(hs); }
		}
		
		return w;
	}

	@GetMapping("/weather/remote")
	public Weather getWeatherRemote(@RequestParam("zip") String zip) {
		RestTemplate restTemplate = new RestTemplate();
		String host = System.getenv("weather_api_host");
		String port = System.getenv("weather_api_port");
		StringBuffer url = new StringBuffer("http://")
			.append(host.trim()).append(":")
			.append(port.trim()).append("/weather?zip=")
			.append(zip.trim());

		restTemplate.getMessageConverters().add(setMessageConverters());
		ResponseEntity responseEntity;
		Weather ws = null ;
		/*
		This whole interaction needs a lot of work.  Not sure
		why the JSon returned isn't parsing...
		*/
		try { 
			responseEntity = restTemplate.getForEntity(url.toString(),
			String.class);
			//ws = (Weather)responseEntity.getBody();
			String wss = (String)responseEntity.getBody();
			StringReader reader = new StringReader(wss);
			JsonParser parser = Json.createParser(reader);
			while (parser.hasNext()) {
				Event event = parser.next();
				wss = parser.getString();
				//System.err.println(wss);
			}
			
			ws = new Weather();
			ws.setZip(zip);
			ws.setFound(true);
			ws.setWeather(wss);
	
		}
		catch (HttpClientErrorException hce) {
			System.err.println(hce.getStatusCode());
			ws = null;
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return ws;

	}

}
