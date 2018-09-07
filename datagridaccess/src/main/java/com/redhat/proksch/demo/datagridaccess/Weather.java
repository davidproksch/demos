package com.redhat.proksch.demo.datagridaccess;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class Weather {

	public Weather() {}
	private String zip;
	private String weather;
	private boolean found;

}
