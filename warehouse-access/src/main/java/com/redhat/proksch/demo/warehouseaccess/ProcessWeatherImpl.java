package com.redhat.proksch.demo.warehouseaccess;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Service("warehousesService")
public class ProcessWeatherImpl implements ProcessWeather {

	private static final String WEATHER_URL="http://weather-mbodemo.6923.rh-us-east-1.openshiftapps.com";
	private static final String WEATHER_PATH="/weather?zip=";

    @Override
    public String getWarehouses( String warehouses ) {
	ObjectMapper mapper = new ObjectMapper();
    List<Map<String,String>> w;
	try {
		w = mapper.readValue(warehouses, List.class);
		w.forEach(ww -> {
				String zip = ww.get("zip");
                System.out.println(augmentWithWeather(zip));
                        });

		System.out.println("place holder");
	}
	catch (Exception e) {
		System.err.println(e.getMessage());
	}
        return new String( warehouses );
    }

    private String augmentWithWeather(String postalCode) {
	    // Check and see if the weather for this postal code
 	    // has already been fetched and is stored in the Data Grid

	    // Make it this far?  Time to go fetch the Weather
		RestTemplate rt = new RestTemplate();
		String s = rt.getForObject(WEATHER_URL + 
			WEATHER_PATH + postalCode.trim(),String.class);


		return s;
    }

	private RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
